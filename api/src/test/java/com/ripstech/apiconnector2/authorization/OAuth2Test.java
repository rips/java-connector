package com.ripstech.apiconnector2.authorization;

import com.ripstech.apiconnector2.Api;
import com.ripstech.apiconnector2.config.HttpClientConfig;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.ApiSettings;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OAuth2Test extends ApiSettings {

	public OAuth2Test() throws IOException, ApiException {
	}

	@Test
	void header() throws ApiException {
		HttpClientConfig httpClientConfig = new HttpClientConfig();
		Map<String, String> authHeader = new OAuth2(BASE_URL, USERNAME, PASSWORD, httpClientConfig).getAuthHeader();
		assertTrue(authHeader.get("Authorization").matches("Bearer .+"));
	}

	@Test
	void connect() throws ApiException {
		Api api = new Api.Builder(BASE_URL).withOAuthv2(USERNAME, PASSWORD).build();
		assertEquals(USERNAME, api.status().get().orThrow(ApiException::new).getUser().getUsername());
	}

}