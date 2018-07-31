package com.ripstech.apiconnector2.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.scan.Concat;
import com.ripstech.apiconnector2.service.template.GetService;

import java.util.List;

public class ConcatService extends GetService<Concat> {

	private final int applicationId;
	private final int scanId;

	public ConcatService(String baseUri, int applicationId, int scanId) {
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
