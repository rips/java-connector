package com.ripstech.apiconnector2.entity.send.application.scan;

public class Batch {

	private Integer startLine;
	private Integer endLine;
	private String name;
	private Integer file;

	public Batch(Integer startLine, Integer endLine, String name, Integer file) {
		this.startLine = startLine;
		this.endLine = endLine;
		this.name = name;
		this.file = file;
	}

	public Integer getStartLine() {
		return startLine;
	}

	public Batch setStartLine(Integer startLine) {
		this.startLine = startLine;
		return this;
	}

	public Integer getEndLine() {
		return endLine;
	}

	public Batch setEndLine(Integer endLine) {
		this.endLine = endLine;
		return this;
	}

	public String getName() {
		return name;
	}

	public Batch setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getFile() {
		return file;
	}

	public Batch setFile(Integer file) {
		this.file = file;
		return this;
	}
}
