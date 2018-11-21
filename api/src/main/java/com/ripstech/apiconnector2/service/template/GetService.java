package com.ripstech.apiconnector2.service.template;

import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.service.queryparameter.Filter;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;

public abstract class GetService<T> extends SimpleGetListService<T> implements GenericTypeable<T> {

	protected GetService(String baseUri ) {
		super(baseUri);
	}

	public ApiResponse<T> get(long id) {
		return get(id, Filter.empty());
	}

	public ApiResponse<T> get(long id, Filter filter) {
		return new ApiResponse<>(getTarget(GET).appendPath(Long.toString(id)).setQueryParams(filter), getGenericType());
	}

}
