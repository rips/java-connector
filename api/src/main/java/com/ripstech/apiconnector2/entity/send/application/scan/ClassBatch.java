package com.ripstech.apiconnector2.entity.send.application.scan;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassBatch extends Batch {

	@JsonProperty("package")
	private String packageName;

	public ClassBatch(Integer startLine, Integer endLine, String name, String packageName, Long file) {
		super(startLine, endLine, name, file);
		this.packageName = packageName;
	}

	public ClassBatch(Batch batch, String packageName) {
		super(batch);
		this.packageName = packageName;
	}

	public String getPackageName() {
		return packageName;
	}

	public ClassBatch setPackageName(String packageName) {
		this.packageName = packageName;
		return this;
	}
}
