package com.ripstech.apiconnector2.entity.receive.application.scan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.receive.application.Scan;

public class Function {

	private int id;
	private String name;
	private Integer startLine;
	private Integer endLine;
	private File file;
	@JsonProperty("class")
	private Class clazz;
	private Scan scan;

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Integer getStartLine() {
		return this.startLine;
	}

	public Integer getEndLine() {
		return this.endLine;
	}

	public File getFile() {
		return this.file;
	}

	public Class getClazz() {
		return this.clazz;
	}

	public Scan getScan() {
		return this.scan;
	}

}
