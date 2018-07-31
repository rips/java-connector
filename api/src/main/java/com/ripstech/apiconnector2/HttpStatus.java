package com.ripstech.apiconnector2;

public class HttpStatus {
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
		return String.format("%d (%s)", code, reason);
	}
}
