package com.ripstech.api.connector.entity.receive.application.scan.issue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ripstech.api.connector.config.Configuration;
import com.ripstech.api.entity.receive.application.scan.issue.Stats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class StatsTest {

	@Test
	void issueDepthMap() throws IOException {
		String json0 = "{ \"issue_depths\":{} }";
		String json1 = "{ \"issue_depths\":{\"0\": 5} }";
		String json2 = "{ \"issue_depths\":{\"0\": 5, \"1\": 20} }";
		String json3 = "{ \"issue_depths\":{\"0\": 5, \"1\": 20, \"2\": 3} }";


		ObjectMapper objectMapper = Configuration.getObjectMapper(Configuration.MAPPER_CONFIG.DEFAULT)
		                                         .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

		Assertions.assertEquals(0, objectMapper.readValue(json0, Stats.class).getIssueDepths().size());
		Assertions.assertEquals(1, objectMapper.readValue(json1, Stats.class).getIssueDepths().size());
		Assertions.assertEquals(2, objectMapper.readValue(json2, Stats.class).getIssueDepths().size());
		Assertions.assertEquals(3, objectMapper.readValue(json3, Stats.class).getIssueDepths().size());
	}

}