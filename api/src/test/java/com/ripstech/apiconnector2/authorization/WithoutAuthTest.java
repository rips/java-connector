package com.ripstech.apiconnector2.authorization;

import com.ripstech.apiconnector2.Api;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.ApiSettings;
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
