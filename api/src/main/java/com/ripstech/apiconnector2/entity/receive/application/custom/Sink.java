package com.ripstech.apiconnector2.entity.receive.application.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.receive.application.Custom;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.Type;

public class Sink {

	private long id;
	@JsonProperty("class")
	private String clazz;
	private String method;
	private String parameter;
	private Type type;
	private Custom custom;

	public long getId() {
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

	public Type getType() {
		return this.type;
	}

	public Custom getCustom() {
		return this.custom;
	}

}
