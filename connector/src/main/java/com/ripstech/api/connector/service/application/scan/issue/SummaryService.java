package com.ripstech.api.connector.service.application.scan.issue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.GetService;
import com.ripstech.api.entity.receive.application.scan.issue.Summary;

import java.util.List;

public class SummaryService extends GetService<Summary> {

	private final long applicationId;
	private final long scanId;
	private final long issueId;

	public SummaryService(String baseUri, long applicationId, long scanId, long issueId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
		this.issueId = issueId;
	}

	@Override
	public TypeReference<Summary> getGenericType() {
		return new TypeReference<Summary>() {};
	}

	@Override
	public TypeReference<List<Summary>> getGenericListType() {
		return new TypeReference<List<Summary>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/issues/%d/summaries", applicationId, scanId, issueId);
	}
}
