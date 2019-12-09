package com.ripstech.api.connector.service.application.scan.issue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.GetService;
import com.ripstech.api.entity.receive.application.scan.issue.Context;

import java.util.List;

public class ContextService extends GetService<Context> {

	private final long applicationId;
	private final long scanId;
	private final long issueId;

	public ContextService(String baseUri, long applicationId, long scanId, long issueId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
		this.issueId = issueId;
	}

	@Override
	public TypeReference<Context> getGenericType() {
		return new TypeReference<Context>() {};
	}

	@Override
	public TypeReference<List<Context>> getGenericListType() {
		return new TypeReference<List<Context>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/issues/%d/contexts", applicationId, scanId, issueId);
	}
}
