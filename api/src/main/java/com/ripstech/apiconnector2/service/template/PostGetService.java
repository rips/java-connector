package com.ripstech.apiconnector2.service.template;

import com.ripstech.apiconnector2.ApiResponse;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.POST;

public abstract class PostGetService<T, K> extends GetService<T> {

	protected PostGetService(String baseUri) {
		super(baseUri);
	}

	public ApiResponse<T> post(K entity) {
		return new ApiResponse<>(getTarget(POST).setJsonBody(entity),
		                         getGenericType());
	}

}
