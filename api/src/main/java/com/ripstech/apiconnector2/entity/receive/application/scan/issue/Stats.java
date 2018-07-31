package com.ripstech.apiconnector2.entity.receive.application.scan.issue;

import java.util.Collections;
import java.util.Map;

public class Stats {

	private Type issueType;
	private Map<String, Integer> issueSeverities = Collections.emptyMap();
	private Map<Integer, Integer> issueDepths = Collections.emptyMap();
	private Integer issues;
	private Integer reviewedIssues;
	private Map<String, Integer> issueTypes = Collections.emptyMap();

	public Type getIssueType() {
		return this.issueType;
	}

	public Map<String, Integer> getIssueSeverities() {
		return this.issueSeverities;
	}

	public Map<Integer, Integer> getIssueDepths() {
		return this.issueDepths;
	}

	public Integer getIssues() {
		return this.issues;
	}

	public Integer getReviewedIssues() {
		return this.reviewedIssues;
	}

	public Map<String, Integer> getIssueTypes() {
		return this.issueTypes;
	}

}
