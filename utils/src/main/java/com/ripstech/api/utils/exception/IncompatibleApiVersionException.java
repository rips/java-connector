package com.ripstech.api.utils.exception;

import com.ripstech.api.connector.exception.ApiException;
import com.ripstech.api.utils.validation.ApiVersion;

public class IncompatibleApiVersionException extends ApiException {

	private final ApiVersion required;
	private final ApiVersion found;

	public IncompatibleApiVersionException(ApiVersion required, ApiVersion found) {
		super(String.format("Incompatible RIPS API version (required: %s, found: %s)", required, found));
		this.required = required;
		this.found = found;
	}

	public ApiVersion getRequired() {
		return required;
	}

	public ApiVersion getFound() {
		return found;
	}
}
