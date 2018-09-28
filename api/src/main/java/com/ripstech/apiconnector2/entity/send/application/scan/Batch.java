package com.ripstech.apiconnector2.entity.send.application.scan;

public class Batch {

	private Integer startLine;
	private Integer endLine;
	private String name;
	private Long file;

	public Batch(Integer startLine, Integer endLine, String name, Long file) {
		this.startLine = startLine;
		this.endLine = endLine;
		this.name = name;
		this.file = file;
	}

	public Batch(Batch batch) {
		this.startLine = batch.startLine;
		this.endLine = batch.endLine;
		this.name = batch.name;
		this.file = batch.file;
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

	public Long getFile() {
		return file;
	}

	public Batch setFile(Long file) {
		this.file = file;
		return this;
	}
}
