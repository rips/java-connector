package com.ripstech.apiconnector2.entity.receive.application.scan;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ripstech.apiconnector2.entity.deserializer.ArrayToMapDeserializer;

import java.util.Collections;
import java.util.Map;

public class Stats {

	private Map<String, Integer> issueSeverities = Collections.emptyMap();
	private Map<Integer, Integer> issueDepths = Collections.emptyMap();
	private Map<String, Integer> issueTypes = Collections.emptyMap();
	private Integer scans;
	private Integer issues;
	private Integer reviewedIssues;

	public Map<String, Integer> getIssueSeverities() {
		return this.issueSeverities;
	}

	@JsonDeserialize(using = ArrayToMapDeserializer.class)
	public Map<Integer, Integer> getIssueDepths() {
		return this.issueDepths;
	}

	public Map<String, Integer> getIssueTypes() {
		return this.issueTypes;
	}

	public Integer getScans() {
		return this.scans;
	}

	public Integer getIssues() {
		return this.issues;
	}

	public Integer getReviewedIssues() {
		return this.reviewedIssues;
	}

}
