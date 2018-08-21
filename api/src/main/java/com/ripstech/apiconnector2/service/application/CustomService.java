package com.ripstech.apiconnector2.service.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.entity.receive.application.Custom;
import com.ripstech.apiconnector2.entity.send.application.CustomSend;
import com.ripstech.apiconnector2.service.queryparameter.Filter;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;

public class CustomService extends PatchDeletePostGetService<Custom, CustomSend> {

	private final long applicationId;

	public CustomService(String baseUri, long applicationId) {
		super(baseUri);
		this.applicationId = applicationId;
	}

	@Override
	public TypeReference<Custom> getGenericType() {
		return new TypeReference<Custom>() {
		};
	}

	@Override
	public TypeReference<List<Custom>> getGenericListType() {
		return new TypeReference<List<Custom>>() {
		};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/customs", applicationId);
	}

	public ApiResponse<List<Custom>> getGlobals(Filter filter) {
		return new ApiResponse<>(getTarget(GET)
				                         .setQueryParams(filter)
				                         .appendPath("globals"),
		                         getGenericListType());
	}

	public ApiResponse<List<Custom>> getGlobals() {
		return getGlobals(Filter.empty());
	}

}
