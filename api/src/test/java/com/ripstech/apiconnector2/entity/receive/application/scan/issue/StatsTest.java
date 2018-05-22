package com.ripstech.apiconnector2.entity.receive.application.scan.issue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ripstech.apiconnector2.config.Configuration;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class StatsTest {

	@Test
	void issueDepthList() throws IOException {
		String json0 = "{ \"issue_depths\":[] }";
		String json1 = "{ \"issue_depths\":[5] }";
		String json2 = "{ \"issue_depths\":[5,20] }";
		String json3 = "{ \"issue_depths\":[5,20,3] }";


		ObjectMapper objectMapper = Configuration.getObjectMapper(Configuration.MAPPER_CONFIG.DEFAULT)
				                            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

		Assert.assertEquals(0, objectMapper.readValue(json0, Stats.class).getIssueDepths().size());
		Assert.assertEquals(1, objectMapper.readValue(json1, Stats.class).getIssueDepths().size());
		Assert.assertEquals(2, objectMapper.readValue(json2, Stats.class).getIssueDepths().size());
		Assert.assertEquals(3, objectMapper.readValue(json3, Stats.class).getIssueDepths().size());


	}

	@Test
	void issueDepthMap() throws IOException {
		String json0 = "{ \"issue_depths\":{} }";
		String json1 = "{ \"issue_depths\":{\"0\": 5} }";
		String json2 = "{ \"issue_depths\":{\"0\": 5, \"1\": 20} }";
		String json3 = "{ \"issue_depths\":{\"0\": 5, \"1\": 20, \"2\": 3} }";


		ObjectMapper objectMapper = Configuration.getObjectMapper(Configuration.MAPPER_CONFIG.DEFAULT)
				                            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

		Assert.assertEquals(0, objectMapper.readValue(json0, Stats.class).getIssueDepths().size());
		Assert.assertEquals(1, objectMapper.readValue(json1, Stats.class).getIssueDepths().size());
		Assert.assertEquals(2, objectMapper.readValue(json2, Stats.class).getIssueDepths().size());
		Assert.assertEquals(3, objectMapper.readValue(json3, Stats.class).getIssueDepths().size());
	}

}