package com.ripstech.api.connector.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.SimpleGetListService;
import com.ripstech.api.entity.receive.application.Scan;

import java.util.List;

public class AllService extends SimpleGetListService<Scan> {

	public AllService(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "/applications/scans/all";
	}

	@Override
	public TypeReference<List<Scan>> getGenericListType() {
		return new TypeReference<List<Scan>>() {};
	}
}
