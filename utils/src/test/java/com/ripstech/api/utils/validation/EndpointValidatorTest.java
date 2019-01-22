package com.ripstech.api.utils.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EndpointValidatorTest {

	@Test
	void validateUrl() {
		Assertions.assertTrue(EndpointValidator.url("https://api.ripstech.com"));
		Assertions.assertTrue(EndpointValidator.url("http://google.com"));
		Assertions.assertTrue(EndpointValidator.url("http://localhost:80"));
		Assertions.assertFalse(EndpointValidator.url(null));
		Assertions.assertTrue(EndpointValidator.url("http://10.0.4.2:8080"));
	}
}
