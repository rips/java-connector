package com.ripstech.apiconnector2.service.template;

import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.service.queryparameter.Filter;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;

public abstract class SimpleGetService<T> extends GenericService implements GenericTypeable<T> {

	protected SimpleGetService(String baseUri) {
		super(baseUri);
	}

	public ApiResponse<T> get(Filter filter) {
		return new ApiResponse<>(getTarget(GET).setQueryParams(filter), getGenericType());
	}

	public ApiResponse<T> get() {
		return get(Filter.empty());
	}

}
