package com.ripstech.apiconnector2.service.application.scan.issue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.Markup;
import com.ripstech.apiconnector2.service.template.GetService;

import java.util.List;

public class MarkupService extends GetService<Markup> {

	private final int applicationId;
	private final int scanId;
	private final int issueId;

	public MarkupService(String baseUri, int applicationId, int scanId, int issueId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
		this.issueId = issueId;
	}

	@Override
	public TypeReference<Markup> getGenericType() {
		return new TypeReference<Markup>() {};
	}

	@Override
	public TypeReference<List<Markup>> getGenericListType() {
		return new TypeReference<List<Markup>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/issues/%d/markups", applicationId, scanId, issueId);
	}
}
