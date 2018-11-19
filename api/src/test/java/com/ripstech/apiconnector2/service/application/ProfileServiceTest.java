package com.ripstech.apiconnector2.service.application;

import com.ripstech.api.entity.send.ApplicationSend;
import com.ripstech.api.entity.send.application.ProfileSend;
import com.ripstech.apiconnector2.Api;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.ApiSettings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ProfileServiceTest extends ApiSettings {

	private static Api api;
	private static long appId;

	public ProfileServiceTest() throws IOException, ApiException {
	}

	@BeforeAll
	static void setUp() throws ApiException {
		api = new Api.Builder(BASE_URL).withHttpClientConfig(HTTP_CLIENT_CONFIG).build();
		appId = api.applications().post(new ApplicationSend.Post(ProfileServiceTest.class.getCanonicalName()))
				        .orThrow(ApiException::new)
				        .getId();
	}

	@AfterAll
	static void cleanUp() {
		api.applications().delete(appId);
	}

	@Test
	void getGlobals() throws ApiException {
		api.application(appId).profiles().getGlobals().orThrow(ApiException::new);
	}

	@Test
	void postPatchDelete() throws ApiException {
		ProfileService service = api.application(appId).profiles();
		long profileId = service.post(new ProfileSend.Post("test")).orThrow(ApiException::new).getId();
		service.patch(profileId, new ProfileSend.Patch().setDefault_(true))
				.process(profile -> assertTrue(profile.getDefault_()), (httpStatus, s) -> fail(s));
		service.delete(profileId);
	}
}