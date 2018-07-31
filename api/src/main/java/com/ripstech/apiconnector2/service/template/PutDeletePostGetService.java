package com.ripstech.apiconnector2.service.template;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.ripstech.apiconnector2.ApiResponse;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.PUT;

public abstract class PutDeletePostGetService<T, K> extends DeletePostGetService<T, K> {

	protected PutDeletePostGetService(String baseUri) {
		super(baseUri);
	}

	public ApiResponse<T> put(K entity) {
		return new ApiResponse<>(getTarget(PUT, entity.getClass().getAnnotation(JsonRootName.class) != null)
				                         .setJsonBody(entity),
		                         getGenericType());
	}

}
