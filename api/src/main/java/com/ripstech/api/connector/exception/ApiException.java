package com.ripstech.api.connector.exception;

import com.ripstech.api.connector.ApiResponse;
import com.ripstech.api.connector.HttpStatus;

public class ApiException extends Exception {

	public ApiException(String message) {
		super(message);
	}

	public ApiException(HttpStatus status, String message) {
		super(String.format("%s: %s", status.toString(), message));
	}

	public ApiException(Integer status, String message) {
		super(String.format("%d: %s", status, message));
	}

	public ApiException(ApiResponse response) {
		this(response.getHttpStatus(), response.getMessage());
	}

}
