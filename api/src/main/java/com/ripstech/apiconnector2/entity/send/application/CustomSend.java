package com.ripstech.apiconnector2.entity.send.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Optional;

@JsonRootName("custom")
public class CustomSend {

	private Optional<String> name;
	private Optional<Boolean> global;
	@JsonProperty("default")
	private Optional<Boolean> default_;

	public static CustomSend createPost(String name) {
		return new CustomSend().setName(name);
	}

	public static CustomSend createPatch() {
		return new CustomSend();
	}

	private CustomSend() {}

	public CustomSend setName(String name) {
		this.name = Optional.ofNullable(name);
		return this;
	}

	public CustomSend setGlobal(Boolean global) {
		this.global = Optional.ofNullable(global);
		return this;
	}

	public CustomSend setDefault_(Boolean default_) {
		this.default_ = Optional.ofNullable(default_);
		return this;
	}

	public Optional<String> getName() {
		return this.name;
	}

	public Optional<Boolean> getGlobal() {
		return this.global;
	}

	public Optional<Boolean> getDefault_() {
		return default_;
	}
}
