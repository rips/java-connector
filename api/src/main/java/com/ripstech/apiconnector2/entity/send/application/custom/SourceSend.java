package com.ripstech.apiconnector2.entity.send.application.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Optional;

@JsonRootName("source")
public class SourceSend {

	@JsonProperty("class")
	Optional<String> clazz;
	Optional<String> method;
	Optional<String> property;
	Optional<String> parameter;
	Optional<String> type;

	public static SourceSend createPost(String type) {
		return new SourceSend().setType(type);
	}

	public static SourceSend createPatch() {
		return new SourceSend();
	}

	private SourceSend() {}

	public SourceSend setClazz(String clazz) {
		this.clazz = Optional.ofNullable(clazz);
		return this;
	}

	public SourceSend setMethod(String method) {
		this.method = Optional.ofNullable(method);
		return this;
	}

	public SourceSend setProperty(String property) {
		this.property = Optional.ofNullable(property);
		return this;
	}

	public SourceSend setParameter(String parameter) {
		this.parameter = Optional.ofNullable(parameter);
		return this;
	}

	public SourceSend setType(String type) {
		this.type = Optional.ofNullable(type);
		return this;
	}

	public Optional<String> getClazz() {
		return this.clazz;
	}

	public Optional<String> getMethod() {
		return this.method;
	}

	public Optional<String> getProperty() {
		return this.property;
	}

	public Optional<String> getParameter() {
		return this.parameter;
	}

	public Optional<String> getType() {
		return this.type;
	}
}
