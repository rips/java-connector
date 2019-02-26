package com.ripstech.api.utils.exception;

import com.ripstech.api.connector.exception.ApiException;

public class InvalidApiCredentialsException extends ApiException {

	public InvalidApiCredentialsException() {
		super("Invalid credentials for RIPS API.");
	}

}
