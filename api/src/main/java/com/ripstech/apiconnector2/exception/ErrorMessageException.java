package com.ripstech.apiconnector2.exception;

import com.ripstech.apiconnector2.entity.receive.ErrorMessage;
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

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
}
