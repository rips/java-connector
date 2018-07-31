package com.ripstech.apiconnector2.service.template;

import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.service.queryparameter.Filter;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.DELETE;

public abstract class DeleteGetService<T> extends GetService<T> {


	protected DeleteGetService(String baseUri) {
		super(baseUri);
	}

	public ApiResponse<Void> delete(Filter filter) {
		return new ApiResponse<>(getTarget(DELETE).setQueryParams(filter), Void.class);
	}

	public ApiResponse<Void> delete(int id) {
		return new ApiResponse<>(getTarget(DELETE).appendPath(id), Void.class);
	}

}
