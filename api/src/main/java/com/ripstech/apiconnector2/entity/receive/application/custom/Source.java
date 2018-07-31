package com.ripstech.apiconnector2.entity.receive.application.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.receive.application.Custom;

public class Source {

	private int id;
	@JsonProperty("class")
	private String clazz;
	private String method;
	private String property;
	private String parameter;
	private String type;
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

	public String getProperty() {
		return this.property;
	}

	public String getParameter() {
		return this.parameter;
	}

	public String getType() {
		return this.type;
	}

	public Custom getCustom() {
		return this.custom;
	}

}
