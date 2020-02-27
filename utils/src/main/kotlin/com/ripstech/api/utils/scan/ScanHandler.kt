package com.ripstech.api.utils.scan

import com.ripstech.api.connector.Api
import com.ripstech.api.connector.exception.ApiException
import com.ripstech.api.connector.service.application.ScanService
import com.ripstech.api.connector.service.queryparameter.Filter
import com.ripstech.api.entity.receive.application.Scan
import com.ripstech.api.entity.receive.application.Upload
import com.ripstech.api.entity.send.application.ScanSend
import com.ripstech.api.utils.MinimalLogging
import com.ripstech.api.utils.file.Archiver
import com.ripstech.api.utils.file.RipsFileFilter
import com.ripstech.api.utils.issue.IssueHandler
import com.ripstech.api.utils.kotlin.Failure
import com.ripstech.api.utils.kotlin.Success
import com.ripstech.api.utils.kotlin.result
import java.io.File
import java.nio.file.Path
import java.util.function.Consumer

class ScanHandler @JvmOverloads constructor(
    private val api: Api,
    val appId: Long,
    var uiUrl: String? = null
) : MinimalLogging() {

    private var upload: Upload? = null
    private var path: String? = null
    private lateinit var scan: Scan

    override fun setLogger(logger: Consumer<String>): ScanHandler {
        super.logger = logger
        return this
    }

    fun setPath(path: String) {
        this.path = path
    }

    @Throws(ApiException::class)
    fun uploadFile(file: File): Upload {
        log("Uploading archive ...")
        when (val result = api.application(appId).uploads().post(file).result()) {
            is Failure -> throw result.exception()
            is Success -> {
                upload = result.value
                log("Archive uploaded successfully (%d)", result.value.id)
                return result.value
            }
        }
    }

    @Throws(ApiException::class)
    @JvmOverloads
    fun uploadFile(
        path: Path,
        archivePrefix: String = "source",
        basePath: Path? = null,
        includeHidden: Boolean = false
    ): Upload {
        val ripsFileFilter = RipsFileFilter(api, appId).apply {
            setIncludeHidden(includeHidden)
        }
        val archiver = when (basePath) {
            null -> Archiver(ripsFileFilter)
            else -> Archiver(ripsFileFilter, basePath)
        }.setLogger(logger)
        val file = archiver.createZip(path, archivePrefix)
        val uploadFile = uploadFile(file)
        archiver.removeZipFile()
        return uploadFile
    }

    @Throws(ApiException::class)
    @JvmOverloads
    fun startScan(
        version: String,
        tags: List<String> = emptyList(),
        scanConfig: Consumer<ScanSend.Post> = Consumer { }
    ): Scan {
        return startScan(version, tags) { scanConfig.accept(this) }
    }

    @Throws(ApiException::class)
    fun startScan(
        version: String,
        scanConfig: Consumer<ScanSend.Post> = Consumer { }
    ): Scan {
        return startScan(version) { scanConfig.accept(this) }
    }

    @Throws(ApiException::class)
    @JvmSynthetic
    fun startScan(
        version: String,
        tags: List<String> = emptyList(),
        scanConfig: ScanSend.Post.() -> Unit = {}
    ): Scan {
        log("Starting scan (Version: %s) ...", version)
        val post = ScanSend.Post(version)
        scanConfig.invoke(post)
        post.profile?.ifPresent { if (it <= 0L) post.setProfile(null) }
        when (val result = api.application(appId).scans().post(
            ScanService.ScanSendPost(post.also {
                upload?.let { upload ->
                    it.setUpload(upload.id)
                } ?: it.setPath(path)
            }).setTags(tags)
        ).result()) {
            is Failure -> throw result.exception()
            is Success -> {
                scan = result.value
                uiUrl?.let { log("UI URL: %s", getScanUrl(it)) }
                return scan
            }
        }
    }

    fun getScanUrl(uiUrl: String) = "%s/scan/%d/%d".format(uiUrl, appId, scan.id)

    @JvmOverloads
    fun getIssueHandler(
        timeoutInMinutes: Long? = null,
        pollIntervalInSeconds: Long? = null,
        allowsCreatedAtFilter: Boolean? = null
    ): IssueHandler {
        return IssueHandler(api, appId, scan.id).apply {
            setLogger(logger)
            timeoutInMinutes?.let { this.timeoutInMinutes = it }
            pollIntervalInSeconds?.let { this.pollIntervalInSeconds = it }
            allowsCreatedAtFilter?.let { this.allowsCreatedAtFilter = it }
        }
    }

    fun getLoc() = api.application(appId)
        .scans()
        .get(scan.id, Filter().select("loc"))
        .map { it.loc }
        .orElse(0)

    @Throws(ApiException::class)
    @JvmOverloads
    fun getProcessesVersion(finished: Boolean = true): Map<String, String> {
        val select = setOf("name", "version", "finished")
        when (val result = api.application(appId).scan(scan.id).processes().get(Filter().select(select)).result()) {
            is Failure -> throw result.exception()
            is Success -> {
                return result.value
                    .filter { !finished || it.finished }
                    .associate {
                        it.name to (it.version ?: "")
                    }
            }
        }
    }

    @Throws(ApiException::class)
    fun getFilePaths(): Map<Long, String> {
        when (val result = api.application(appId).scan(scan.id).files().get(Filter().select("id", "path")).result()) {
            is Failure -> throw result.exception()
            is Success -> return result.value.associate { it.id to it.path }
        }
    }

    @Throws(ApiException::class)
    @JvmOverloads
    fun getFunctionSignatures(simple: Boolean = false): Map<Long, String> {
        if (!simple) TODO("Implement full qualified signatures")
        when (val result = api.application(appId).scan(scan.id).functions().get(Filter().select("id", "name", "parameters")).result()) {
            is Failure -> throw result.exception()
            is Success -> return result.value.associate {
                it.id to it.name + it.parameters.joinToString(prefix = "(", postfix = ")") { parameter -> parameter.name }
            }
        }
    }

    @Throws(ApiException::class)
    @JvmOverloads
    fun getClassSignatures(simple: Boolean = false): Map<Long, String> {
        when (val result = api.application(appId).scan(scan.id).classes().get(Filter().select("id", "name", "package")).result()) {
            is Failure -> throw result.exception()
            is Success -> return result.value.associate {
                it.id to (if (simple) "" else "${it.package_}.") + it.name
            }
        }
    }
}
