package com.ripstech.apiconnector2.entity.send.application.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Optional;

@JsonRootName("validator")
public class ValidatorSend {

	@JsonProperty("class")
	Optional<String> clazz;
	Optional<String> method;
	Optional<String> parameter;
	Optional<String> characters;

	public static ValidatorSend createPost() {
		return new ValidatorSend();
	}

	public static ValidatorSend createPatch() {
		return new ValidatorSend();
	}

	private ValidatorSend() {}

	public ValidatorSend setClazz(String clazz) {
		this.clazz = Optional.ofNullable(clazz);
		return this;
	}

	public ValidatorSend setMethod(String method) {
		this.method = Optional.ofNullable(method);
		return this;
	}

	public ValidatorSend setParameter(String parameter) {
		this.parameter = Optional.ofNullable(parameter);
		return this;
	}

	public ValidatorSend setCharacters(String characters) {
		this.characters = Optional.ofNullable(characters);
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

	public Optional<String> getCharacters() {
		return this.characters;
	}
}
