package com.ripstech.api.connector.exception;

import com.ripstech.api.connector.ApiResponse;
import com.ripstech.api.connector.HttpStatus;

import java.util.Arrays;

public class ApiException extends Exception {

	private final ProblemType problemType;

	public ApiException(String message) {
		super(message);
		problemType = ProblemType.UNKNOWN;
	}

	public ApiException(HttpStatus status, String message) {
		super(String.format("%s: %s", status.toString(), message));
		problemType = ProblemType.findProblemTypeOrUnknown(status.getCode(), message);
	}

	public ApiException(Integer status, String message) {
		super(String.format("%d: %s", status, message));
		problemType = ProblemType.findProblemTypeOrUnknown(status, message);
	}

	public ApiException(ApiResponse response) {
		this(response.getHttpStatus(), response.getMessage());
	}

	public ProblemType getProblemType() {
		return problemType;
	}

	public enum ProblemType {
		INVALID_INPUT(400, "Invalid input"),
		TOO_MANY_ISSUES_OF_THIS_TYPE(400, "Too many issues of this type"),
		NO_PARENT_SCAN_FOUND(400, "No parent scan found"),
		MISSING_PERMISSIONS(403, "Missing permissions"),
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
