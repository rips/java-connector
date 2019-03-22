package com.ripstech.api.connector.exception;

import com.ripstech.api.connector.entity.receive.ErrorMessage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ErrorMessageException extends Exception {

	private final ErrorMessage errorMessage;

	public ErrorMessageException(@Nullable final ErrorMessage errorMessage) {
		if(errorMessage == null) {
			this.errorMessage = new ErrorMessage();
		} else {
			this.errorMessage = errorMessage;
		}
	}

	public ErrorMessageException(final int code, @NotNull final String body) {
		this.errorMessage = new ErrorMessage();
		this.errorMessage.setCode(code);
		this.errorMessage.setMessage(body);
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
}
