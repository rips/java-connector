package com.ripstech.apiconnector2.service.queryparameter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.ripstech.apiconnector2.service.queryparameter.JsonFilter.and;
import static com.ripstech.apiconnector2.service.queryparameter.JsonFilter.equal;

class FilterTest {

	@Test
	void ordering() {
		StringBuilder s = new StringBuilder();
		new Filter()
				.json(equal("abc", 4))
				.limit(2)
				.getParams()
				.forEach((s1, s2) -> s.append(s1).append("=").append(s2).append("&"));
		Assertions.assertEquals("filter={\"__equal\":{\"abc\":4}}&limit=2&", s.toString());

		StringBuilder s0 = new StringBuilder();
		new Filter()
				.limit(2)
				.json(equal("abc", 4))
				.getParams()
				.forEach((s1, s2) -> s0.append(s1).append("=").append(s2).append("&"));
		Assertions.assertEquals("limit=2&filter={\"__equal\":{\"abc\":4}}&", s0.toString());
	}

	@Test
	void json() {
		StringBuilder s = new StringBuilder();
		new Filter()
				.json(
						and(
								equal("test", 1),
								equal("tset", 2)
						   )
				     )
				.readable()
				.getParams()
				.forEach((s1, s2) -> s.append(s1).append("=").append(s2).append("&"));
		Assertions.assertEquals("filter={\"__and\":[{\"__equal\":{\"test\":1}},{\"__equal\":{\"tset\":2}}]}&showIssueReadable=1&", s.toString());
	}

}