package com.ripstech.api.utils.version

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ScanVersionPatternTest {

	val input = ScanVersionPattern.getPlaceHolder()
		.filter { s -> s != ScanVersionPattern.ISO_DATE_TIME }
		.sorted()
		.joinToString(separator = "|")

	@Test
	fun replace() {
		assertEquals(
			"{branch}|{buildNumber}|Test System|{commit}|{longCommit}|{projectKey}|{projectName}",
			ScanVersionPattern("Test System").replace(input)
		)
		val version = ScanVersionPattern("Test System", 1L, "Project Name", "Project Key", "Branch", "fa18df91a28c7a688ad6f3ec430293a4190b9e8e")
		assertEquals(
			"Branch|1|Test System|fa18df9|fa18df91a28c7a688ad6f3ec430293a4190b9e8e|Project Key|Project Name",
			version.replace(input)
		)
	}

}