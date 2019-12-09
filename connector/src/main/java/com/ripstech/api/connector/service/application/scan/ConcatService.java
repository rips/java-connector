package com.ripstech.api.connector.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.GetService;
import com.ripstech.api.entity.receive.application.scan.Concat;

import java.util.List;

public class ConcatService extends GetService<Concat> {

	private final long applicationId;
	private final long scanId;

	public ConcatService(String baseUri, long applicationId, long scanId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
	}

	@Override
	public TypeReference<Concat> getGenericType() {
		return new TypeReference<Concat>() {};
	}

	@Override
	public TypeReference<List<Concat>> getGenericListType() {
		return new TypeReference<List<Concat>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/concats", applicationId, scanId);
	}

}
