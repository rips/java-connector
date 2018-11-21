package com.ripstech.apiconnector2.service;

import com.ripstech.api.entity.receive.Application;
import com.ripstech.api.entity.send.ApplicationSend;
import com.ripstech.apiconnector2.Api;
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
		Application application = service.post(new ApplicationSend.Post(ApplicationServiceTest.class.getCanonicalName()))
				                          .orThrow(ApiException::new);
		long id = application
				         .getId();
		String changedName = ApplicationServiceTest.class.getName() + "changed";
		assertEquals(changedName,
		             service.patch(id, new ApplicationSend.Patch().setName(changedName))
				             .orThrow(ApiException::new)
				             .getName());
		service.delete(id);
	}

}
