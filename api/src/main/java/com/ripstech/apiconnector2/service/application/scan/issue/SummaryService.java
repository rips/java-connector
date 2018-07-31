package com.ripstech.apiconnector2.service.application.scan.issue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.Summary;
import com.ripstech.apiconnector2.service.template.GetService;

import java.util.List;

public class SummaryService extends GetService<Summary> {

	private final int applicationId;
	private final int scanId;
	private final int issueId;

	public SummaryService(String baseUri, int applicationId, int scanId, int issueId) {
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
