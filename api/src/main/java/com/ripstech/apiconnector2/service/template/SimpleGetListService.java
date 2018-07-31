package com.ripstech.apiconnector2.service.template;

import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.service.queryparameter.Filter;

import java.util.List;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;

public abstract class SimpleGetListService<T> extends GenericService implements GenericListTypeable<T> {

	protected SimpleGetListService(String baseUri) {
		super(baseUri);
	}

	public ApiResponse<List<T>> get(Filter filter) {
		return new ApiResponse<>(getTarget(GET).setQueryParams(filter), getGenericListType());
	}

	public ApiResponse<List<T>> get() {
		return get(Filter.empty());
	}

}
