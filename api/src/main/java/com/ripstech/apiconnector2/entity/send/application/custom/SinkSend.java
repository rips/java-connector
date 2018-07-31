package com.ripstech.apiconnector2.entity.send.application.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Optional;

@JsonRootName("sink")
public class SinkSend {

	@JsonProperty("class")
	Optional<String> clazz;
	Optional<String> method;
	Optional<String> parameter;
	Optional<Integer> type;

	public static SinkSend createPost(int type) {
		return new SinkSend().setType(type);
	}

	public static SinkSend createPatch() {
		return new SinkSend();
	}

	private SinkSend() {}

	public SinkSend setClazz(String clazz) {
		this.clazz = Optional.ofNullable(clazz);
		return this;
	}

	public SinkSend setMethod(String method) {
		this.method = Optional.ofNullable(method);
		return this;
	}

	public SinkSend setParameter(String parameter) {
		this.parameter = Optional.ofNullable(parameter);
		return this;
	}

	public SinkSend setType(Integer type) {
		this.type = Optional.ofNullable(type);
		return this;
	}

	public Optional<String> getClazz() {
		return this.clazz;
	}

	public Optional<String> getMethod() {
		return this.method;
	}

	public Optional<String> getParameter() {
		return this.parameter;
	}

	public Optional<Integer> getType() {
		return this.type;
	}
}
