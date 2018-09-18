package com.ripstech.apiconnector2.entity.receive.application.scan;

import com.ripstech.apiconnector2.entity.receive.application.Scan;

public class Class {

	private long id;
	private Integer startLine;
	private Integer endLine;
	private String name;
	private File file;
	private Scan scan;

	public long getId() {
		return this.id;
	}

	public Integer getStartLine() {
		return this.startLine;
	}

	public Integer getEndLine() {
		return this.endLine;
	}

	public String getName() {
		return this.name;
	}

	public File getFile() {
		return file;
	}

	public Scan getScan() {
		return scan;
	}
}
