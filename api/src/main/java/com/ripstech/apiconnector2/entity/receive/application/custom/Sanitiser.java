package com.ripstech.apiconnector2.entity.receive.application.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.receive.application.Custom;

public class Sanitiser {

	private int id;
	@JsonProperty("class")
	private String clazz;
	private String method;
	private String parameter;
	private String characters;
	private Custom custom;

	public int getId() {
		return this.id;
	}

	public String getClazz() {
		return this.clazz;
	}

	public String getMethod() {
		return this.method;
	}

	public String getParameter() {
		return this.parameter;
	}

	public String getCharacters() {
		return this.characters;
	}

	public Custom getCustom() {
		return this.custom;
	}

}
