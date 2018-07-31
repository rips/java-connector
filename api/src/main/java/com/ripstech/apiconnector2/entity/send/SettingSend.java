package com.ripstech.apiconnector2.entity.send;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "setting")
public class SettingSend {

	private String value;

	public static SettingSend createPut(String value) {
		return new SettingSend().setValue(value);
	}

	private SettingSend() {}

	public SettingSend setValue(String value) {
		this.value = value;
		return this;
	}

	public String getValue() {
		return this.value;
	}

}
