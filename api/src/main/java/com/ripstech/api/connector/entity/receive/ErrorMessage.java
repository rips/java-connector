package com.ripstech.api.connector.entity.receive;

public class ErrorMessage {

	private Integer code;
	private String message;
	private Object errors;

	public String getMessageAndErrors() {
		String ret = "";
		ret += (message != null ? message : "");
		ret += (errors != null ? errors.toString() : "");
		return ret;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public Object getErrors() {
		return this.errors;
	}
}
