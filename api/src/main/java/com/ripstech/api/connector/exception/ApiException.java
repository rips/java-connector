package com.ripstech.api.connector.exception;

import com.ripstech.api.connector.ApiResponse;
import com.ripstech.api.connector.HttpStatus;

import java.util.Arrays;

import static com.ripstech.api.connector.HttpStatus.NON_HTTP_ERROR;

public class ApiException extends Exception {

	private final ProblemType problemType;
	private final String plainMessage;

	public ApiException(String message) {
		super(message);
		plainMessage = message;
		problemType = ProblemType.UNKNOWN;
	}

	public ApiException(HttpStatus status, String message) {
		super(String.format("%s: %s", status.toString(), message));
		plainMessage = message;
		problemType = ProblemType.findProblemTypeOrUnknown(status.getCode(), message);
	}

	public ApiException(Integer status, String message) {
		super((status != NON_HTTP_ERROR) ? String.format("%d: %s", status, message) : message);
		plainMessage = message;
		problemType = ProblemType.findProblemTypeOrUnknown(status, message);
	}

	public ApiException(ApiResponse response) {
		this(response.getHttpStatus(), response.getMessage());
	}

	public ProblemType getProblemType() {
		return problemType;
	}

	public String getPlainMessage() {
		if(plainMessage.isEmpty()) {
			return getMessage();
		}
		return plainMessage;
	}

	public enum ProblemType {
		INVALID_INPUT(400, "Invalid input"),
		TOO_MANY_ISSUES_OF_THIS_TYPE(400, "Too many issues of this type"),
		YOU_HAVE_TO_SET_PATH_OR_UPLOAD(400, "You have to set path or upload"),
		NO_PARENT_SCAN_FOUND(400, "No parent scan found"),
		INVALID_CREDENTIALS(401, "Invalid credentials"),
		MISSING_PERMISSIONS(403, "Missing permissions"),
		NOT_AUTHORIZED_TO_PERFORM_THIS_ACTION(403, "Not authorized to perform this action"),
		NOT_FOUND(404, "Not found"),
		ANOTHER_EXPORT_IS_CURRENTLY_RUNNING(423, "Another export is currently running"),
		TOO_MANY_CREATE_ATTEMPTS(429, "Too many create attempts"),
		TOO_MANY_RESET_ATTEMPTS(429, "Too many reset attempts"),
		UNKNOWN(-1, "");

		ProblemType(int httpCode, String responseMessage) {
			this.httpCode = httpCode;
			this.responseMessage = responseMessage;
		}

		private int httpCode;
		private String responseMessage;

		public static ProblemType findProblemTypeOrUnknown(int httpCode, String responseMessage) {
			return Arrays.stream(ProblemType.values())
			             .filter(p -> p.httpCode == httpCode)
			             .filter(p -> responseMessage.contains(p.responseMessage))
			             .findFirst()
			             .orElse(UNKNOWN);
		}

	}

}
