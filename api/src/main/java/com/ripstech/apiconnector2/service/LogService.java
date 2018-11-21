package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.Log;
import com.ripstech.api.entity.send.LogSend;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.service.queryparameter.Filter;
import com.ripstech.apiconnector2.service.template.PostGetService;

import java.util.List;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.DELETE;

public class LogService extends PostGetService<Log, LogSend> {

	public LogService(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "logs";
	}

	@Override
	public TypeReference<Log> getGenericType() {
		return new TypeReference<Log>() {};
	}

	@Override
	public TypeReference<List<Log>> getGenericListType() {
		return new TypeReference<List<Log>>() {};
	}

	public ApiResponse<Void> delete(Filter filter) {
		return new ApiResponse<>(getTarget(DELETE)
				                         .addQueryParams(filter),
		                         Void.class);
	}

	public ApiResponse<Void> delete() {
		return delete(Filter.empty());
	}
}
