package com.ripstech.api.connector;

public class HttpStatus {

	public static final int NON_HTTP_ERROR = -1;

	private final int code;
	private String reason;

	HttpStatus(int code, String reason) {
		this.code = code;
		this.reason = reason;
	}

	public int getCode() {
		return code;
	}

	public String getReason() {
		return reason;
	}

	@Override
	public String toString() {
		if(code != NON_HTTP_ERROR) {
			return String.format("%d (%s)", code, reason);
		} else {
			return reason;
		}
	}
}
