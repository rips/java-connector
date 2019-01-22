package com.ripstech.api.connector.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.PostGetService;
import com.ripstech.api.entity.receive.License;
import com.ripstech.api.entity.send.LicenseSend;

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
