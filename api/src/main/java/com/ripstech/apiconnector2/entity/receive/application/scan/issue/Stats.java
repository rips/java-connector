package com.ripstech.apiconnector2.entity.receive.application.scan.issue;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ripstech.apiconnector2.entity.deserializer.ArrayToMapDeserializer;

import java.util.Collections;
import java.util.Map;

public class Stats {

	private Type issueType;
	private Map<String, Integer> issueSeverities = Collections.emptyMap();
	private Map<Integer, Integer> issueDepths = Collections.emptyMap();
	private Long issues;
	private Long reviewedIssues;
	private Map<String, Integer> issueTypes = Collections.emptyMap();

	public Type getIssueType() {
		return this.issueType;
	}

	public Map<String, Integer> getIssueSeverities() {
		return this.issueSeverities;
	}

	@JsonDeserialize(using = ArrayToMapDeserializer.class)
	public Map<Integer, Integer> getIssueDepths() {
		return this.issueDepths;
	}

	public Long getIssues() {
		return this.issues;
	}

	public Long getReviewedIssues() {
		return this.reviewedIssues;
	}

	public Map<String, Integer> getIssueTypes() {
		return this.issueTypes;
	}

}
