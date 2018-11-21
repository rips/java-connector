package com.ripstech.apiconnector2.service.template;

import com.ripstech.apiconnector2.ApiResponse;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.PATCH;

public abstract class PatchDeletePostGetService<T, K> extends DeletePostGetService<T, K> {

	protected PatchDeletePostGetService(String baseUri) {
		super(baseUri);
	}

	public ApiResponse<T> patch(long id, K entity) {
		return new ApiResponse<>(getTarget(PATCH).appendPath(id).setJsonBody(entity),
		                         getGenericType());
	}
}
