package com.ripstech.apiconnector2.constant;

@SuppressWarnings("unused")
public enum MarkupType {
	TEXT("TEXT"),
	INJECTION("INJECTION");

	private String value;

	MarkupType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
