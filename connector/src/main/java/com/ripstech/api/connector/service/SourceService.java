package com.ripstech.api.connector.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.ApiResponse;
import com.ripstech.api.connector.service.template.GenericService;

import java.util.List;

import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.GET;

public class SourceService extends GenericService {

	public SourceService(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "sources";
	}

	public ApiResponse<List<String>> get() {
		return new ApiResponse<>(getTarget(GET), new TypeReference<List<String>>() {});
	}

}
