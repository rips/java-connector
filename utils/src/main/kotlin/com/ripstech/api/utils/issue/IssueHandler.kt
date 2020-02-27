package com.ripstech.api.utils.issue

import com.ripstech.api.connector.Api
import com.ripstech.api.connector.Phase
import com.ripstech.api.connector.exception.ApiException
import com.ripstech.api.connector.service.queryparameter.Filter
import com.ripstech.api.connector.service.queryparameter.JsonFilter.greaterThan
import com.ripstech.api.entity.receive.application.scan.Issue
import com.ripstech.api.utils.MinimalLogging
import com.ripstech.api.utils.constant.RipsDefault
import com.ripstech.api.utils.kotlin.Failure
import com.ripstech.api.utils.kotlin.Success
import com.ripstech.api.utils.kotlin.result
import com.ripstech.api.utils.scan.result.ScanResult
import com.ripstech.api.utils.scan.result.ScanResultParser
import com.ripstech.api.utils.validation.ApiVersion
import java.time.Duration
import java.util.concurrent.TimeoutException
import java.util.function.Consumer
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

class IssueHandler @JvmOverloads constructor(
    private val api: Api,
    private val appId: Long,
    private val scanId: Long,
    var timeoutInMinutes: Long = RipsDefault.SCAN_TIMEOUT_IN_MINUTES,
    var pollIntervalInSeconds: Long = 10,
    var allowsCreatedAtFilter: Boolean = false
) : MinimalLogging() {

    companion object {
        val API_VERSION_333: ApiVersion = ApiVersion.parse("3.3.3")
    }

    init {
        when (val result = api.status().get().result()) {
            is Success -> result.value.version
            is Failure -> null
        }?.let {
            runCatching {
                ApiVersion.parse(it)
            }.onSuccess {
                allowsCreatedAtFilter = it.isGreaterEqualThan(API_VERSION_333)
            }
        }
    }

    override fun setLogger(logger: Consumer<String>): IssueHandler {
        super.logger = logger
        return this
    }

    @Throws(ApiException::class, TimeoutException::class)
    @JvmOverloads
    fun getScanResult(filter: Filter = Filter()): ScanResult {
        blockWhileFinished()
        return ScanResultParser.getResult(api, appId, scanId, filter)
    }

    @Throws(ApiException::class, TimeoutException::class)
    @JvmOverloads
    fun getAllIssues(
        filter: Filter = Filter()
    ): List<Issue> {
        blockWhileFinished()
        return when (val result = api.application(appId).scan(scanId).issues().get(filter).result()) {
            is Failure -> {
                throw result.exception()
            }
            is Success -> {
                result.value.toList()
            }
        }
    }

    @Throws(ApiException::class, TimeoutException::class)
    @JvmOverloads
    fun processAllIssues(
        filter: Filter = Filter(),
        issueAction: Consumer<Issue>,
        chunkAction: Runnable
    ) {
        processAllIssues(
                filter,
                { issueAction.accept(this) },
                { chunkAction.run() }
        )
    }

    @Throws(ApiException::class, TimeoutException::class)
    @JvmSynthetic
    fun processAllIssues(
        filter: Filter = Filter(),
        issueAction: Issue.() -> Unit,
        chunkAction: () -> Unit = { }
    ) {
        runBlocking {
            checkProgressWhileFinished(0L) {
                fetchIssuesAndProcess(
                        filter,
                        it,
                        issueAction::invoke,
                        chunkAction
                )
            }
        }
    }

    suspend fun processAllIssuesAsync(
        filter: Filter = Filter(),
        issueAction: Issue.() -> Unit,
        chunkAction: () -> Unit = { }
    ) {
        checkProgressWhileFinished(0L) {
            fetchIssuesAndProcess(
                    filter,
                    it,
                    issueAction::invoke,
                    chunkAction
            )
        }
    }

    private fun fetchIssuesAndProcess(
        filter: Filter = Filter(),
        offset: Long,
        issueAction: (Issue) -> Unit,
        chunkAction: () -> Unit
    ): Long {
        if (allowsCreatedAtFilter) {
            filter.orderBy("createdAt", Filter.Order.ASC).offset(offset)
        } else {
            filter.orderBy("id").json(greaterThan("id", offset))
        }
        return when (val result = api.application(appId)
                .scan(scanId)
                .issues()
                .get(filter)
                .result()) {
            is Failure -> throw result.exception()
            is Success -> {
                result.value.forEach(issueAction)
                if (allowsCreatedAtFilter) {
                    result.value.size.toLong()
                } else {
                    result.value.map { it.id }.max() ?: 0
                }.also { chunkAction() }
            }
        }
    }

    @Throws(ApiException::class, TimeoutException::class)
    private fun blockWhileFinished() = runBlocking {
        checkProgressWhileFinished(Unit) {}
    }

    private suspend fun <T> checkProgressWhileFinished(
        initValue: T,
        action: (T) -> T
    ) {
        withTimeoutOrNull(Duration.ofMinutes(timeoutInMinutes).toMillis()) {
            var progress = Progress(-1, Phase.UNKNOWN)
            var failCounter = 0
            var lastPercent = progress.percent
            var actionState = initValue
            while (!progress.isScanFinished() && isActive) {
                runCatching {
                    progress = getScanProgress()
                }.onFailure {
                    if (failCounter++ >= 10) {
                        throw ApiException("More than 10 requests failed, while waiting for the scan to finish: " +
                                it.message)
                    }
                }.onSuccess {
                    launch {
                        actionState = action.invoke(actionState)
                    }
                    if (!(progress.isScanFinished() && lastPercent == -1)) {
                        if (progress.percent != lastPercent) {
                            log(progress.toString())
                            lastPercent = progress.percent
                        }
                    }
                }
                delay(Duration.ofSeconds(pollIntervalInSeconds).toMillis())
            }
            action.invoke(actionState)
        }
                ?: throw TimeoutException("Reached timeout of $timeoutInMinutes minutes, while waiting for the scan to finish.")
    }

    @Throws(ApiException::class)
    private fun getScanProgress(): Progress {
        return when (val result = api
                .application(appId)
                .scans().get(scanId, Filter.empty().select("percent", "phase"))
                .result()) {
            is Failure -> throw result.exception()
            is Success -> Progress(result.value.percent, Phase.getById(result.value.phase))
        }
    }
}
