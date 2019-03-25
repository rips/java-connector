package com.ripstech.api.connector.authorization;

import com.ripstech.api.connector.Api;
import com.ripstech.api.connector.exception.ApiException;
import com.ripstech.api.connector.service.ApiSettings;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XPasswordWithApiSettingsTest extends ApiSettings {

	public XPasswordWithApiSettingsTest() throws IOException, ApiException {
	}

	@Test
	void connect() throws ApiException {
		Api api = new Api.Builder(BASE_URL).withXPassword(USERNAME, PASSWORD).build();
		assertEquals(USERNAME, api.status().get().orThrow(ApiException::new).getUser().getEmail());
	}

}