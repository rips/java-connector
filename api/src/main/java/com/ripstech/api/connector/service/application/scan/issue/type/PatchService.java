package com.ripstech.api.connector.service.application.scan.issue.type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.GetService;
import com.ripstech.api.entity.receive.application.scan.issue.type.Patch;

import java.util.List;

public class PatchService extends GetService<Patch> {

	public PatchService(String baseUri) {
		super(baseUri);
	}

	@Override
	public TypeReference<List<Patch>> getGenericListType() {
		return new TypeReference<List<Patch>>() {};
	}

	@Override
	public TypeReference<Patch> getGenericType() {
		return new TypeReference<Patch>() {};
	}

	@Override
	protected String getPath() {
		return "applications/scans/issues/types/patches";
	}
}
