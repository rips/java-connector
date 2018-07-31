package com.ripstech.apiconnector2.entity.receive.application.scan.issue;

import com.ripstech.apiconnector2.entity.receive.application.scan.Issue;

public class Markup {

	private int id;
	private String markup;
	private Issue issue;

	public int getId() {
		return this.id;
	}

	public String getMarkup() {
		return this.markup;
	}

	public Issue getIssue() {
		return this.issue;
	}

}
