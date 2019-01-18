package com.ripstech.api.utils.file

import com.ripstech.apiconnector2.Api
import com.ripstech.apiconnector2.exception.ApiException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FileExtensionTest {

	@Test
	fun test() {
		val api = Api.Builder("http://localhost:8020")
				.withXPassword("user@ripstech.com", "devmode")
				.build()
		assertTrue(FileExtension.getByAppId (api, 1).size > 1)
		println(FileExtension.getByAppId (api, 1))
		assertThrows(ApiException::class.java) {
			FileExtension.getByAppId(api, Long.MAX_VALUE)
		}
	}



}