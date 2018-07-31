package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.License;
import com.ripstech.apiconnector2.entity.send.LicenseSend;
import com.ripstech.apiconnector2.service.template.PostGetService;

import java.util.List;

public class LicenseService extends PostGetService<License, LicenseSend> {

	public LicenseService(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "licenses";
	}

	@Override
	public TypeReference<License> getGenericType() {
		return new TypeReference<License>() {};
	}

	@Override
	public TypeReference<List<License>> getGenericListType() {
		return new TypeReference<List<License>>() {};
	}
}
