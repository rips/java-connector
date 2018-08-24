package com.ripstech.apiconnector2.entity.send.application.scan.issue;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("review")
public class ReviewSend {

	private Long type;
	private String source;

	public static ReviewSend createPost(long type) {
		return new ReviewSend().setType(type);
	}

	private ReviewSend() {}

	public Long getType() {
		return this.type;
	}

	public String getSource() {
		return this.source;
	}

	public ReviewSend setType(Long type) {
		this.type = type;
		return this;
	}

	public ReviewSend setSource(String source) {
		this.source = source;
		return this;
	}
}
