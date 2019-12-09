package com.ripstech.api.connector.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.Status;
import com.ripstech.api.connector.service.template.SimpleGetService;

public class StatusService extends SimpleGetService<Status> {

	public StatusService(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "status";
	}

	@Override
	public TypeReference<Status> getGenericType() {
		return new TypeReference<Status>() {};
	}
}
