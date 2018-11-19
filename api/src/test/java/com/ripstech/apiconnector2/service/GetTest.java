package com.ripstech.apiconnector2.service;

import com.ripstech.api.entity.send.ApplicationSend;
import com.ripstech.apiconnector2.Api;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.exception.ApiException;
import com.ripstech.apiconnector2.service.template.GenericService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class GetTest extends ApiSettings {

	private static List<Class<? extends GenericService>> servicesCache = null;

	private static Api api;
	private static long appId = 0;

	GetTest() throws IOException, ApiException {
	}

	@BeforeAll
	static void setUp() throws ApiException {
		api = new Api.Builder(BASE_URL).withHttpClientConfig(HTTP_CLIENT_CONFIG).build();
		appId = api.applications().post(new ApplicationSend.Post(GetTest.class.getCanonicalName())).orThrow(ApiException::new).getId();
	}

	@AfterAll
	static void cleanUp() {
		api.applications().delete(appId);
	}

	private static boolean tryOrFalse(Callable supplier) {
		try {
			supplier.call();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private static Predicate<Class<? extends GenericService>> simpleGet
			= aClass -> tryOrFalse(() -> aClass.getMethod("get"));

	private static List<Class<? extends GenericService>> services() {
		if(servicesCache == null) {
			servicesCache = new Reflections("com.ripstech.apiconnector2.service")
					                .getSubTypesOf(GenericService.class).stream()
					                .filter(aClass -> !aClass.toString().contains("template"))
									.filter(aClass -> !aClass.toString().contains("CommentAllService"))
					                .collect(Collectors.toList());
		}
		return servicesCache;
	}

	private void check(Class<GenericService> clazz, GenericService service) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Method get = clazz.getMethod("get");

		service.setHttpClientConfig(HTTP_CLIENT_CONFIG);
		ApiResponse response = (ApiResponse) get.invoke(service);

		Assertions.assertTrue(response.isOk() || response.isForbidden(), response::getMessage);
		if(response.isForbidden()) {
			System.out.println(response.getMessage());
		}

		if(response.isForbidden()) {
			if(tryOrFalse(() -> clazz.getMethod("get", Long.TYPE))) {
				Method getWithId = clazz.getMethod("get", Long.TYPE);
				ApiResponse response2 = (ApiResponse) getWithId.invoke(service, 0);
				System.out.println(response2.getStatus());
				Assertions.assertTrue(response2.isOk() || response2.isNotFound() || response2.isForbidden(),
				                      response2::getMessage);
			}
		}
	}

	private static Stream<Arguments> getServiceProvider() {
		return services().stream()
				       .filter(simpleGet)
					   .filter(aClass -> tryOrFalse(() -> aClass.getConstructor(String.class)))
				       .map(Arguments::of);
	}

	@ParameterizedTest
	@MethodSource("getServiceProvider")
	void get(Class<GenericService> clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		GenericService service = clazz.getConstructor(String.class).newInstance(BASE_URL);

		check(clazz, service);
	}

	private static Stream<Arguments> getWithAppIdServiceProvider() {
		return services().stream()
				       .filter(simpleGet)
				       .filter(aClass -> tryOrFalse(() -> aClass.getConstructor(String.class, Long.TYPE))
				                         && Arrays.asList(aClass.getName().split("\\.")).contains("application"))
				       .map(Arguments::of);
	}

	@ParameterizedTest
	@MethodSource("getWithAppIdServiceProvider")
	void getWithAppId(Class<GenericService> clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		GenericService service = clazz.getConstructor(String.class, Long.TYPE).newInstance(BASE_URL, appId);

		check(clazz, service);
	}

}
