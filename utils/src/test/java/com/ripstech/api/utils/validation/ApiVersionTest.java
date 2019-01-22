package com.ripstech.api.utils.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiVersionTest {

	private ApiVersion api2_13 = ApiVersion.parse("2.13.0");
	private ApiVersion api2_13_dev_0 = ApiVersion.parse("2.13-dev-0");
	private ApiVersion api3_0_0 = ApiVersion.parse("3.0.0");
	private ApiVersion api3_0_dev_0 = ApiVersion.parse("3.0-dev-0");

	@Test
	void compareTo() {
		assertFalse(api2_13.compareTo(api3_0_0) >= 0);
		assertFalse(api2_13.compareTo(api3_0_dev_0) >= 0);
		//assertTrue(api2_13.compareTo(api2_13) >= 0); Commented because of errorprone SelfComparision error
		assertTrue(api2_13.compareTo(api2_13_dev_0) >= 0);
	}

	@Test
	void isGreaterEqualThan() {
		assertFalse(api2_13.isGreaterEqualThan(api3_0_0));
		assertTrue(api3_0_0.isGreaterEqualThan(api3_0_0));
	}
}