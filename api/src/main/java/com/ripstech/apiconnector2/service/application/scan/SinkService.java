package com.ripstech.apiconnector2.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.scan.Sink;
import com.ripstech.apiconnector2.service.template.GetService;

import java.util.List;

public class SinkService extends GetService<Sink> {

	private final int applicationId;
	private final int scanId;

	public SinkService(String baseUri, int applicationId, int scanId) {
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
