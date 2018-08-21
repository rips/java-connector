package com.ripstech.apiconnector2.entity.receive.application.scan.issue;

import com.ripstech.apiconnector2.entity.receive.User;
import com.ripstech.apiconnector2.entity.receive.application.scan.Issue;

import java.time.OffsetDateTime;

public class Review {

	private long id;
	private OffsetDateTime submission;
	private User createdBy;
	private Issue issue;
	private ReviewType type;
	private String source;

	public long getId() {
		return this.id;
	}

	public OffsetDateTime getSubmission() {
		return this.submission;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

	public Issue getIssue() {
		return this.issue;
	}

	public ReviewType getType() {
		return this.type;
	}

	public String getSource() {
		return this.source;
	}
}
