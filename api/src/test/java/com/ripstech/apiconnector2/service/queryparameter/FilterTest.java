package com.ripstech.apiconnector2.service.queryparameter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilterTest {

	@Test
	void ordering() {
		StringBuilder s = new StringBuilder();
		new Filter()
				.isEqual("abc", 4)
				.limit(2)
				.getParams()
				.forEach((s1, s2) -> s.append(s1).append("=").append(s2).append("&"));
		Assertions.assertEquals("equal[abc]=4&limit=2&", s.toString());

		StringBuilder s0 = new StringBuilder();
		new Filter()
				.limit(2)
				.isEqual("abc", 4)
				.getParams()
				.forEach((s1, s2) -> s0.append(s1).append("=").append(s2).append("&"));
		Assertions.assertEquals("limit=2&equal[abc]=4&", s0.toString());
	}

}