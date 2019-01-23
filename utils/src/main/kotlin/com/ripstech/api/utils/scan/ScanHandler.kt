package com.ripstech.api.utils.scan

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
import com.ripstech.api.connector.Api
import com.ripstech.api.connector.exception.ApiException
import com.ripstech.api.connector.service.application.ScanService
import com.ripstech.api.connector.service.queryparameter.Filter
import java.io.File
import java.nio.file.Path
import java.util.function.Consumer

class ScanHandler constructor(
	private val api: Api,
	private val appId: Long
): MinimalLogging()  {

	private lateinit var upload: Upload
	private lateinit var scan: Scan

	override fun setLogger(logger: Consumer<String>): ScanHandler {
		super.logger = logger
		return this
	}

	@Throws(ApiException::class)
	fun uploadFile(file: File): Upload {
		log("Uploading archive ...")
		when(val result = api.application(appId).uploads().post(file).result()) {
			is Failure -> throw result.exception()
			is Success -> {
				upload = result.value
				log("Archive uploaded successfully (%d)", upload.id)
				return upload
			}
		}
	}

	@Throws(ApiException::class)
	fun uploadFile(path: Path): Upload {
		val archiver = Archiver(RipsFileFilter(api, appId))
			.setLogger(logger)
		val file = archiver.createZip(path)
		uploadFile(file)
		archiver.removeZipFile()
		return upload
	}

	@Throws(ApiException::class)
	@JvmOverloads
	fun startScan(
		version: String,
		analysisDepth: Int = 5,
		profileId: Long? = null,
		source: String? = null,
		tags: List<String> = emptyList(),
		uiUrl: String? = null
	): Scan {
		log("Starting scan (Version: %s) ...", version)
		when(val result = api.application(appId).scans().post(
			ScanService.ScanSendPost(
				ScanSend.Post(version)
					.setUpload(upload.id)
					.setAnalysisDepth(analysisDepth)
					.setProfile(profileId)
					.setSource(source))
				.setTags(tags)
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
		pollIntervalInSeconds: Long? = null
	): IssueHandler {
		return IssueHandler(api, appId, scan.id).apply {
			setLogger(logger)
			timeoutInMinutes?.let { this.timeoutInMinutes = it }
			pollIntervalInSeconds?.let { this.pollIntervalInSeconds = it }
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
		if(!simple) TODO("Implement full qualified signatures")
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