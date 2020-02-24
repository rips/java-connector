package com.ripstech.api.utils.application

import com.ripstech.api.connector.Api
import com.ripstech.api.connector.entity.send.filter.condition.And
import com.ripstech.api.connector.entity.send.filter.condition.Or
import com.ripstech.api.connector.entity.send.filter.expression.Equal
import com.ripstech.api.connector.exception.ApiException
import com.ripstech.api.connector.service.queryparameter.Filter
import com.ripstech.api.entity.receive.Quota
import com.ripstech.api.entity.send.ApplicationSend
import com.ripstech.api.utils.MinimalLogging
import com.ripstech.api.utils.kotlin.Failure
import com.ripstech.api.utils.kotlin.Success
import com.ripstech.api.utils.kotlin.result
import com.ripstech.api.utils.scan.ScanHandler
import java.time.OffsetDateTime
import java.util.function.Consumer

class ApplicationHandler @JvmOverloads constructor(
        private val api: Api,
        private val appName: String,
        private val language: String,
        private val uiUrl: String? = null
): MinimalLogging() {

    override fun setLogger(logger: Consumer<String>): ApplicationHandler {
        super.logger = logger
        return this
    }

    private fun getOrCreateByNameAndLang(appName: String, language: String): Long {
        val quotas = getUsableQuotasForLang(language)

        val quotaFilter = Or(quotas.map { Equal("chargedQuota", it.id) })
        val filter = Filter().json(And(Equal("name", appName), quotaFilter))
        val application = when(val appResult = api.applications().get(filter).result()) {
            is Failure -> throw appResult.exception()
            is Success -> appResult.value.firstOrNull()?.also {
                log("Use existing application (${it.id}) for scanning.")
            }
        } ?: run {
            val validQuota = quotas.bestMatch() ?: error(
                    "No valid quota for language '$language' to create app with name '$'"
            )
            val appPayload = ApplicationSend.Post(appName).setChargedQuota(validQuota.id)
            when(val appCreateResult = api.applications().post(appPayload).result()) {
                is Failure -> throw appCreateResult.exception()
                is Success -> appCreateResult.value.also {
                    log("Use created application (${it.id}) with quota (${validQuota.id}) for scanning.")
                }
            }
        }
        return application.id
    }

    private fun getUsableQuotasForLang(language: String): List<Quota> {
        val quotas = when (val quotasResult = api.quotas().get().result()) {
            is Failure -> throw quotasResult.exception()
            is Success -> quotasResult.value.filter { quota ->
                language.toLowerCase() in quota.languages.map { it.name.toLowerCase() }
            }.filter { it.canScan() }
        }
        require(quotas.isNotEmpty()) {
            "Not valid quotas for language '$language' found."
        }
        return quotas
    }

    fun getScanHandler(): ScanHandler {
        val appId = getOrCreateByNameAndLang(appName, language)
        return ScanHandler(api, appId, uiUrl).apply {
            setLogger(logger)
        }
    }

    companion object {

        @JvmStatic
        @Throws(ApiException::class)
        fun getApplications(api: Api): UserApplications {
            return getApplications(api, Filter.empty())
        }

        @JvmStatic
        @Throws(ApiException::class)
        fun getApplications(api: Api, filter: Filter?): UserApplications = api.applications()
                .get(filter)
                .orThrow { s, m -> ApiException(s, m) }
                .associate {
                    it.id to UserApplications.Entry(it, it.chargedQuota.languages.map { it.name }.firstOrNull() ?: "unknown")
                }.let {
                    UserApplications(it)
                }

    }

}

private fun List<Quota>.bestMatch(): Quota? = filter { it.canCreateAppAndScan() }
        .firstOrNull { it.maxApplications == null }
        ?: maxBy { it.maxApplications - it.currentApplication }

private fun Quota.canCreateAppAndScan(): Boolean {
    if(!canScan()) return false
    maxApplications?.let { if(currentApplication >= it) return false }
    return true
}

private fun Quota.canScan(): Boolean {
    if(validFrom > OffsetDateTime.now() || validUntil < OffsetDateTime.now()) return false
    maxScans?.let { if(currentScan >= it) return false }
    return true
}