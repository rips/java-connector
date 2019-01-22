package com.ripstech.api.connector.service.template;

import com.ripstech.api.connector.ApiResponse;
import com.ripstech.api.connector.service.queryparameter.Filter;

public abstract class SimpleGetService<T> extends GenericService implements GenericTypeable<T> {

	protected SimpleGetService(String baseUri) {
		super(baseUri);
	}

	public ApiResponse<T> get(Filter filter) {
		return new ApiResponse<>(getTarget(HttpMethod.GET).setQueryParams(filter), getGenericType());
	}

	public ApiResponse<T> get() {
		return get(Filter.empty());
	}

}
