package com.ripstech.apiconnector2.service;

import com.ripstech.apiconnector2.Api;
import com.ripstech.apiconnector2.exception.ApiException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class SettingServiceTest extends ApiSettings {

	private static Api api;

	SettingServiceTest() throws IOException, ApiException {
	}

	@BeforeAll
	static void setUp() throws ApiException {
		api = new Api.Builder(BASE_URL).withHttpClientConfig(HTTP_CLIENT_CONFIG).build();
	}

	@Test
	void putDelete() throws ApiException {
		api.settings().put("test",  "test").orThrow(ApiException::new);
		api.settings().delete("test").orThrow(ApiException::new);
	}
}