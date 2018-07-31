package com.ripstech.apiconnector2.entity.send.application.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Optional;

@JsonRootName("ignore")
public class IgnoreSend {

	@JsonProperty("class")
	private Optional<String> clazz;
	private Optional<String> method;
	private Optional<String> type;
	private Optional<String> folder;
	private Optional<String> fullPath;
	private Optional<String> codeQualityFolder;

	public static IgnoreSend createPost() {
		return new IgnoreSend();
	}

	public static IgnoreSend createPatch() {
		return new IgnoreSend();
	}


	private IgnoreSend() {}

	public IgnoreSend setClazz(String clazz) {
		this.clazz = Optional.ofNullable(clazz);
		return this;
	}

	public IgnoreSend setMethod(String method) {
		this.method = Optional.ofNullable(method);
		return this;
	}

	public IgnoreSend setType(String type) {
		this.type = Optional.ofNullable(type);
		return this;
	}

	public IgnoreSend setFolder(String folder) {
		this.folder = Optional.ofNullable(folder);
		return this;
	}

	public IgnoreSend setFullPath(String fullPath) {
		this.fullPath = Optional.ofNullable(fullPath);
		return this;
	}

	public IgnoreSend setCodeQualityFolder(String codeQualityFolder) {
		this.codeQualityFolder = Optional.ofNullable(codeQualityFolder);
		return this;
	}

	public Optional<String> getClazz() {
		return this.clazz;
	}

	public Optional<String> getMethod() {
		return this.method;
	}

	public Optional<String> getType() {
		return this.type;
	}

	public Optional<String> getFolder() {
		return this.folder;
	}

	public Optional<String> getFullPath() {
		return this.fullPath;
	}

	public Optional<String> getCodeQualityFolder() {
		return codeQualityFolder;
	}
}
