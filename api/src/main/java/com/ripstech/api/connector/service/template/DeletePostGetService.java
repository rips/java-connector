package com.ripstech.api.connector.service.template;

import com.ripstech.api.connector.ApiResponse;
import com.ripstech.api.connector.service.queryparameter.Filter;

import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.DELETE;

public abstract class DeletePostGetService<T, K> extends PostGetService<T, K> {


	protected DeletePostGetService(String baseUri) {
		super(baseUri);
	}

	public ApiResponse<Void> delete(Filter filter) {
		return new ApiResponse<>(getTarget(DELETE).setQueryParams(filter), Void.class);
	}

	public ApiResponse<Void> delete(long id) {
		return new ApiResponse<>(getTarget(DELETE).appendPath(id), Void.class);
	}

}
