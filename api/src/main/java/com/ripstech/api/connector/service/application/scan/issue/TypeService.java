package com.ripstech.api.connector.service.application.scan.issue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.annotation.AuthRequired;
import com.ripstech.api.connector.service.application.scan.issue.type.PatchService;
import com.ripstech.api.connector.service.template.GetService;
import com.ripstech.api.entity.receive.application.scan.issue.Type;

import java.util.List;

@AuthRequired(false)
public class TypeService extends GetService<Type> {

	public TypeService(String baseUri) {
		super(baseUri);
	}

	@Override
	public TypeReference<Type> getGenericType() {
		return new TypeReference<Type>() {};
	}

	@Override
	public TypeReference<List<Type>> getGenericListType() {
		return new TypeReference<List<Type>>() {};
	}

	@Override
	protected String getPath() {
		return "applications/scans/issues/types";
	}

	public com.ripstech.api.connector.service.application.scan.issue.type.PatchService patches() {
		com.ripstech.api.connector.service.application.scan.issue.type.PatchService service = new PatchService(baseUri);
		service.setHttpClientConfig(httpClientConfig);
		return service;
	}
}
