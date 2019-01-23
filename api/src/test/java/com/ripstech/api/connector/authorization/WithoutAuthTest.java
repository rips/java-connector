package com.ripstech.api.connector.authorization;

import com.ripstech.api.connector.Api;
import com.ripstech.api.connector.exception.ApiException;
import com.ripstech.api.connector.service.ApiSettings;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNull;

class WithoutAuthTest extends ApiSettings {

	public WithoutAuthTest() throws IOException, ApiException {
	}

	@Test
	void connect() throws ApiException {
		Api api = new Api.Builder(BASE_URL).build();
		assertNull(api.status().get().orThrow(ApiException::new).getUser());
	}

}
