package com.ripstech.apiconnector2.entity.receive.application.scan;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ripstech.apiconnector2.entity.deserializer.ArrayToMapDeserializer;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Map;

public class Stats {

	private Map<String, Integer> issueSeverities = Collections.emptyMap();
	private Map<Integer, Integer> issueDepths = Collections.emptyMap();
	private Map<String, Integer> issueTypes = Collections.emptyMap();
	private BigInteger scans;
	private BigInteger issues;
	private BigInteger reviewedIssues;

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

	public BigInteger getScans() {
		return this.scans;
	}

	public BigInteger getIssues() {
		return this.issues;
	}

	public BigInteger getReviewedIssues() {
		return this.reviewedIssues;
	}

}
