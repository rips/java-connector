package com.ripstech.api.connector;

import com.github.jenspiegsa.wiremockextension.Managed;
import com.github.jenspiegsa.wiremockextension.WireMockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.ripstech.api.connector.exception.ApiException;
import com.ripstech.api.connector.service.queryparameter.Filter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.github.jenspiegsa.wiremockextension.ManagedWireMockServer.with;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(WireMockExtension.class)
class ApiResponseTest {

	@Managed
	private static WireMockServer server = with(wireMockConfig().dynamicPort());

	@BeforeAll
	static void setupServer() {
		server.givenThat(get(urlMatching("/languages.*"))
				                 .willReturn(okForContentType("text/html", "<html></html>")
						                             .withStatus(500)));
		server.givenThat(get(urlMatching("/status.*"))
				                 .willReturn(aResponse().withStatus(500)
				                                        .withBody("<html></html>")));
		server.givenThat(get(urlMatching("/callbacks.*"))
				                 .willReturn(ok()));
		server.givenThat(get(urlMatching("/applications.*"))
				                 .willReturn(okJson("{'}")));
	}

	@Test
	void receiveAHttp500() {
		ApiException exception = assertThrows(ApiException.class,
		                                      () -> new Api.Builder(server.baseUrl())
				                                      .build()
				                                      .languages()
				                                      .get()
				                                      .orThrow(ApiException::new));
		assertEquals("500 (Server Error): ", exception.getMessage());
		assertEquals("500 (Server Error): ", exception.getPlainMessage());
		assertEquals(ApiException.ProblemType.UNKNOWN, exception.getProblemType());

		exception = assertThrows(ApiException.class,
		                                      () -> new Api.Builder(server.baseUrl())
				                                      .build()
				                                      .status()
				                                      .get(Filter.empty().limit(1))
				                                      .orThrow(ApiException::new));
		assertEquals("500 (Server Error): Unknown media type to display more information", exception.getMessage());
		assertEquals("Unknown media type to display more information", exception.getPlainMessage());
		assertEquals(ApiException.ProblemType.UNKNOWN, exception.getProblemType());
	}

	@Test
	void receiveWithoutMediaType() {
		ApiException exception = assertThrows(ApiException.class,
		                                      () -> new Api.Builder(server.baseUrl())
				                                      .build()
				                                      .callbacks()
				                                      .get()
				                                      .orThrow(ApiException::new));
		assertEquals("200 (OK): Unknown media type to display more information", exception.getMessage());
		assertEquals("Unknown media type to display more information", exception.getPlainMessage());
		assertEquals(ApiException.ProblemType.UNKNOWN, exception.getProblemType());
	}

	@Test
	void receiveWithInvalidJson() {
		ApiException exception = assertThrows(ApiException.class,
		                                      () -> new Api.Builder(server.baseUrl())
				                                      .build()
				                                      .applications()
				                                      .get()
				                                      .orThrow(ApiException::new));
		assertEquals("MismatchedInputException: Cannot deserialize instance of `java.util.ArrayList` out of START_OBJECT token", exception.getMessage());
		assertEquals("Cannot deserialize instance of `java.util.ArrayList` out of START_OBJECT token", exception.getPlainMessage());
		assertEquals(ApiException.ProblemType.UNKNOWN, exception.getProblemType());
	}

}