package com.ripstech.api.connector.authorization;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class XPasswordTest {

	@Test
	void getAuthHeader() {
		Map<String, String> authHeader = new XPassword("user", "pass").getAuthHeader();
		assertEquals("user", authHeader.get("X-Api-Email"));
		assertEquals("pass", authHeader.get("X-Api-Password"));
		assertEquals(base64Encode("user"), authHeader.get("X-Api-Email-Enc"));
		assertEquals(base64Encode("pass"), authHeader.get("X-Api-Password-Enc"));
	}

	@Test
	void getAuthHeaderWithSpecialChars() {
		final String pass = "ÉhNí-:G®¥SÍ@ûfÐ²7b8Ô¢23¨xQ»ì¨²6{";
		Map<String, String> authHeader = new XPassword("user", pass).getAuthHeader();
		assertNull(authHeader.get("X-Api-Email"));
		assertNull(authHeader.get("X-Api-Password"));
		assertEquals(base64Encode("user"), authHeader.get("X-Api-Email-Enc"));
		assertEquals(base64Encode(pass), authHeader.get("X-Api-Password-Enc"));
	}

	private String base64Encode(String s) {
		return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
	}

}