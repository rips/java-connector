package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.service.template.GenericService;

import java.util.List;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;

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
