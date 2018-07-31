package com.ripstech.apiconnector2.authorization;

import com.ripstech.apiconnector2.Api;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.ApiSettings;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XPasswordTest extends ApiSettings {

	public XPasswordTest() throws IOException, ApiException {
	}

	@Test
	void header() {
		Map<String, String> authHeader = new XPassword("user", "pass").getAuthHeader();
		assertEquals("user", authHeader.get("X-Api-Username"));
		assertEquals("pass", authHeader.get("X-Api-Password"));
	}

	@Test
	void connect() throws ApiException {
		Api api = new Api.Builder(BASE_URL).withXPassword(USERNAME, PASSWORD).build();
		assertEquals(USERNAME, api.status().get().orThrow(ApiException::new).getUser().getUsername());
	}

}