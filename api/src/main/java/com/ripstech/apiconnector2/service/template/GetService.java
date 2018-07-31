package com.ripstech.apiconnector2.service.template;

import com.ripstech.apiconnector2.ApiResponse;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;

public abstract class GetService<T> extends SimpleGetListService<T> implements GenericTypeable<T> {

	protected GetService(String baseUri ) {
		super(baseUri);
	}

	public ApiResponse<T> get(int id) {
		return new ApiResponse<>(getTarget(GET).appendPath(Integer.toString(id)), getGenericType());
	}

}
