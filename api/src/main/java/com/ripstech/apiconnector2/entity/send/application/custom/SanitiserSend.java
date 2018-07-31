package com.ripstech.apiconnector2.entity.send.application.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Optional;

@JsonRootName("sanitiser")
public class SanitiserSend {

	@JsonProperty("class")
	Optional<String> clazz;
	Optional<String> method;
	Optional<String> parameter;
	Optional<String> characters;

	public static SanitiserSend createPost() {
		return new SanitiserSend();
	}

	public static SanitiserSend createPatch() {
		return new SanitiserSend();
	}


	private SanitiserSend() {}

	public SanitiserSend setClazz(String clazz) {
		this.clazz = Optional.ofNullable(clazz);
		return this;
	}

	public SanitiserSend setMethod(String method) {
		this.method = Optional.ofNullable(method);
		return this;
	}

	public SanitiserSend setParameter(String parameter) {
		this.parameter = Optional.ofNullable(parameter);
		return this;
	}

	public SanitiserSend setCharacters(String characters) {
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
