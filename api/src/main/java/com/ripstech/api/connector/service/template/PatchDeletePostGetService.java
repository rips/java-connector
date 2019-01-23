package com.ripstech.api.connector.service.template;

import com.ripstech.api.connector.ApiResponse;

import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.PATCH;

public abstract class PatchDeletePostGetService<T, K> extends DeletePostGetService<T, K> {

	protected PatchDeletePostGetService(String baseUri) {
		super(baseUri);
	}

	public ApiResponse<T> patch(long id, K entity) {
		return new ApiResponse<>(getTarget(PATCH).appendPath(id).setJsonBody(entity),
		                         getGenericType());
	}
}
