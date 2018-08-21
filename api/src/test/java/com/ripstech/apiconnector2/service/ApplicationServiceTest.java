package com.ripstech.apiconnector2.service;

import com.ripstech.apiconnector2.Api;
import com.ripstech.apiconnector2.entity.receive.Application;
import com.ripstech.apiconnector2.entity.send.ApplicationSend;
import com.ripstech.apiconnector2.exception.ApiException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationServiceTest extends ApiSettings {

	private static Api api;

	ApplicationServiceTest() throws IOException, ApiException {
	}

	@BeforeAll
	static void setUp() throws ApiException {
		api = new Api.Builder(BASE_URL).withHttpClientConfig(HTTP_CLIENT_CONFIG).build();
	}

	@Test
	void postPatchDelete() throws ApiException {
		ApplicationService service = api.applications();
		Application application = service.post(ApplicationSend.createPost(ApplicationServiceTest.class.getCanonicalName()))
				                          .orThrow(ApiException::new);
		long id = application
				         .getId();
		String changedName = ApplicationServiceTest.class.getName() + "changed";
		assertEquals(changedName,
		             service.patch(id, ApplicationSend.createPatch().setName(changedName))
				             .orThrow(ApiException::new)
				             .getName());
		service.delete(id);
	}

}
