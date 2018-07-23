package com.ripstech.apiconnector2.entity.send;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;
import java.util.Optional;

@JsonRootName(value = "application")
public class ApplicationSend {

	private Optional<String> name;
	private Optional<Integer> chargedQuota;
	private Optional<List<String>> defaultScanCallbacks;
	private Optional<Boolean> defaultExtendedScanCallbacks;

	public static ApplicationSend createPost(String name) {
		return new ApplicationSend().setName(name);
	}

	public static ApplicationSend createPatch() {
		return new ApplicationSend();
	}

	private ApplicationSend() {}

	public ApplicationSend setName(String name) {
		this.name = Optional.ofNullable(name);
		return this;
	}

	public ApplicationSend setChargedQuota(Integer chargedQuota) {
		this.chargedQuota = Optional.ofNullable(chargedQuota);
		return this;
	}

	public ApplicationSend setDefaultScanCallbacks(List<String> defaultScanCallbacks) {
		this.defaultScanCallbacks = Optional.ofNullable(defaultScanCallbacks);
		return this;
	}

	public ApplicationSend setDefaultExtendedScanCallbacks(Boolean defaultExtendedScanCallbacks) {
		this.defaultExtendedScanCallbacks = Optional.ofNullable(defaultExtendedScanCallbacks);
		return this;
	}

	public Optional<String> getName() {
		return this.name;
	}

	public Optional<Integer> getChargedQuota() {
		return this.chargedQuota;
	}

	public Optional<List<String>> getDefaultScanCallbacks() {
		return this.defaultScanCallbacks;
	}

	public Optional<Boolean> getDefaultExtendedScanCallbacks() {
		return defaultExtendedScanCallbacks;
	}
}
