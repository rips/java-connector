package com.ripstech.apiconnector2.service.template;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.ripstech.apiconnector2.ApiResponse;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.PATCH;

public abstract class PatchDeletePostGetService<T, K> extends DeletePostGetService<T, K> {

	protected PatchDeletePostGetService(String baseUri) {
		super(baseUri);
	}

	public ApiResponse<T> patch(int id, K entity) {
		return new ApiResponse<>(getTarget(PATCH, entity.getClass().getAnnotation(JsonRootName.class) != null)
				                         .appendPath(id)
				                         .setJsonBody(entity),
		                         getGenericType());
	}
}
