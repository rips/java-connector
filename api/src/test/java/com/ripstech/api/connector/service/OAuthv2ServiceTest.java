package com.ripstech.api.connector.service;

import com.ripstech.api.connector.entity.send.ClientIdSend;
import com.ripstech.api.connector.exception.ApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

class OAuthv2ServiceTest extends ApiSettings {

	OAuthv2ServiceTest() throws IOException, ApiException {
	}

	@Test
	void getAuthToken() {
		OAuthv2Service service = new OAuthv2Service(BASE_URL);
		service.setHttpClientConfig(HTTP_CLIENT_CONFIG);
		service.getGlobalClientIds().process(ids -> ids.forEach((name, id) -> {
			service.getAuthToken(ClientIdSend.toPost(id, USERNAME, PASSWORD))
					.process(token -> assertNotNull(token.getAccessToken()), (httpStatus, s) -> Assertions.fail(s));
		}), (httpStatus, s) -> Assertions.fail(s));
	}

}
