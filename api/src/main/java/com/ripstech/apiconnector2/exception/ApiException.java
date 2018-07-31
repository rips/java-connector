package com.ripstech.apiconnector2.exception;

import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.HttpStatus;

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
