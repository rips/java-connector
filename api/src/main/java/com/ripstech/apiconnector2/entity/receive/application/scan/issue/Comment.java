package com.ripstech.apiconnector2.entity.receive.application.scan.issue;

import com.ripstech.apiconnector2.entity.receive.User;
import com.ripstech.apiconnector2.entity.receive.application.scan.Issue;

import java.time.OffsetDateTime;

public class Comment {

	private long id;
	private String comment;
	private OffsetDateTime submission;
	private Issue issue;
	private User createdBy;
	private String source;

	public long getId() {
		return this.id;
	}

	public String getComment() {
		return this.comment;
	}

	public OffsetDateTime getSubmission() {
		return this.submission;
	}

	public Issue getIssue() {
		return this.issue;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

	public String getSource() {
		return this.source;
	}
}
