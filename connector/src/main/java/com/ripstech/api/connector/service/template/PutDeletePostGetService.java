package com.ripstech.api.connector.service.template;

import com.ripstech.api.connector.ApiResponse;

import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.PUT;

public abstract class PutDeletePostGetService<T, K> extends DeletePostGetService<T, K> {

	protected PutDeletePostGetService(String baseUri) {
		super(baseUri);
	}

	public ApiResponse<T> put(K entity) {
		return new ApiResponse<>(getTarget(PUT).setJsonBody(entity), getGenericType());
	}

}
