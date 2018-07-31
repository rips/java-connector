package com.ripstech.apiconnector2.entity.receive.application.scan.issue;

import com.ripstech.apiconnector2.entity.receive.application.scan.File;
import com.ripstech.apiconnector2.entity.receive.application.scan.Issue;

public class Summary {

	private int id;
	private String content;
	private String highlightedContent;
	private Integer line;
	private File file;
	private Issue issue;

	public int getId() {
		return this.id;
	}

	public String getContent() {
		return this.content;
	}

	public String getHighlightedContent() {
		return this.highlightedContent;
	}

	public Integer getLine() {
		return this.line;
	}

	public File getFile() {
		return this.file;
	}

	public Issue getIssue() {
		return this.issue;
	}

}
