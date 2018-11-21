package com.ripstech.apiconnector2.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.application.scan.Source;
import com.ripstech.apiconnector2.service.template.GetService;

import java.util.List;

public class SourceService extends GetService<Source> {

	private final long applicationId;
	private final long scanId;

	public SourceService(String baseUri, long applicationId, long scanId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
	}

	@Override
	public TypeReference<Source> getGenericType() {
		return new TypeReference<Source>() {};
	}

	@Override
	public TypeReference<List<Source>> getGenericListType() {
		return new TypeReference<List<Source>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/sources", applicationId, scanId);
	}

}
