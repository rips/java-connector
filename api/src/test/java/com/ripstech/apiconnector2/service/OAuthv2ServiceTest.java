package com.ripstech.apiconnector2.service;

import com.ripstech.apiconnector2.entity.send.ClientIdSend;
import com.ripstech.apiconnector2.exception.ApiException;
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
					.process(token -> assertNotNull(token.getAccessToken()), (httpStatus, s) -> fail(s));
		}), (httpStatus, s) -> fail(s));
	}

}
