package com.ripstech.apiconnector2.service.application;

import com.ripstech.apiconnector2.Api;
import com.ripstech.apiconnector2.entity.send.ApplicationSend;
import com.ripstech.apiconnector2.entity.send.application.CustomSend;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.ApiSettings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class CustomServiceTest extends ApiSettings {

	private static Api api;
	private static long appId;

	public CustomServiceTest() throws IOException, ApiException {
	}

	@BeforeAll
	static void setUp() throws ApiException {
		api = new Api.Builder(BASE_URL).withHttpClientConfig(HTTP_CLIENT_CONFIG).build();
		appId = api.applications().post(ApplicationSend.createPost(CustomServiceTest.class.getCanonicalName()))
				        .orThrow(ApiException::new)
				        .getId();
	}

	@AfterAll
	static void cleanUp() {
		api.applications().delete(appId);
	}

	@Test
	void getGlobals() throws ApiException {
		api.application(appId).customs().getGlobals().orThrow(ApiException::new);
	}

	@Test
	void postPatchDelete() throws ApiException {
		CustomService service = api.application(appId).customs();
		long profileId = service.post(CustomSend.createPost("test")).orThrow(ApiException::new).getId();
		service.patch(profileId, CustomSend.createPatch().setGlobal(true))
				.process(custom -> assertTrue(custom.getGlobal()), (httpStatus, s) -> fail(s));
		service.delete(profileId);
	}
}