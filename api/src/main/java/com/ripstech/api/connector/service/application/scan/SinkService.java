package com.ripstech.api.connector.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.GetService;
import com.ripstech.api.entity.receive.application.scan.Sink;

import java.util.List;

public class SinkService extends GetService<Sink> {

	private final long applicationId;
	private final long scanId;

	public SinkService(String baseUri, long applicationId, long scanId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
	}

	@Override
	public TypeReference<Sink> getGenericType() {
		return new TypeReference<Sink>() {};
	}

	@Override
	public TypeReference<List<Sink>> getGenericListType() {
		return new TypeReference<List<Sink>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/sinks", applicationId, scanId);
	}

}
