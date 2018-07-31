package com.ripstech.apiconnector2.entity.receive.application.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.receive.application.Custom;

public class Ignore {

	private int id;
	@JsonProperty("class")
	private String clazz;
	private String method;
	private String type;
	private String folder;
	private String fullPath;
	private String codeQualityFolder;
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

	public String getType() {
		return this.type;
	}

	public String getFolder() {
		return this.folder;
	}

	public String getFullPath() {
		return this.fullPath;
	}

	public String getCodeQualityFolder() {
		return codeQualityFolder;
	}

	public Custom getCustom() {
		return this.custom;
	}

}
