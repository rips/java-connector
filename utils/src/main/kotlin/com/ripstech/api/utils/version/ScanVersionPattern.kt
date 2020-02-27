package com.ripstech.api.utils.version

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ScanVersionPattern @JvmOverloads constructor(
    private val buildSystem: String,
    private val buildNumber: Long? = null,
    private val projectName: String? = null,
    private val projectKey: String? = null,
    private val branch: String? = null,
    private val longCommit: String? = null
) {

    companion object {
        const val ISO_DATE_TIME = "{isoDateTime}"
        const val BUILD_SYSTEM = "{buildSystem}"
        const val BUILD_NUMBER = "{buildNumber}"
        const val PROJECT_NAME = "{projectName}"
        const val PROJECT_KEY = "{projectKey}"
        const val BRANCH = "{branch}"
        const val COMMIT = "{commit}"
        const val LONG_COMMIT = "{longCommit}"
        val DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault())!!

        @JvmStatic
        fun getPlaceHolder(): Set<String> {
            return setOf(ISO_DATE_TIME, BUILD_SYSTEM, BUILD_NUMBER, PROJECT_NAME, PROJECT_KEY, BRANCH, COMMIT,
                    LONG_COMMIT)
        }
    }

    fun replace(pattern: String): String {
        var returnPattern = pattern
        returnPattern = returnPattern.replace(ISO_DATE_TIME, DTF.format(Instant.now()))
        returnPattern = returnPattern.replace(BUILD_SYSTEM, buildSystem)
        buildNumber?.let { returnPattern = returnPattern.replace(BUILD_NUMBER, it.toString()) }
        projectName?.let { returnPattern = returnPattern.replace(PROJECT_NAME, it) }
        projectKey?.let { returnPattern = returnPattern.replace(PROJECT_KEY, it) }
        branch?.let { returnPattern = returnPattern.replace(BRANCH, it) }
        longCommit?.let { returnPattern = returnPattern.replace(COMMIT, it.take(7)) }
        longCommit?.let { returnPattern = returnPattern.replace(LONG_COMMIT, it) }
        return returnPattern
    }
}
