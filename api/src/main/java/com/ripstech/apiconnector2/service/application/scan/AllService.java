package com.ripstech.apiconnector2.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.Scan;
import com.ripstech.apiconnector2.service.template.SimpleGetListService;

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
