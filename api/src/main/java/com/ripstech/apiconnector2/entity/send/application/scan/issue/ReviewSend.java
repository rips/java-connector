package com.ripstech.apiconnector2.entity.send.application.scan.issue;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("review")
public class ReviewSend {

	private Integer type;

	public static ReviewSend createPost(int type) {
		return new ReviewSend().setType(type);
	}

	private ReviewSend() {}

	public Integer getType() {
		return this.type;
	}

	public ReviewSend setType(Integer type) {
		this.type = type;
		return this;
	}
}
