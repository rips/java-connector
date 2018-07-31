package com.ripstech.apiconnector2.entity.send.application.scan.issue;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("comment")
public class CommentSend {

	private String comment;

	public static CommentSend createPost(String comment) {
		return new CommentSend().setComment(comment);
	}

	private CommentSend() {}

	public String getComment() {
		return this.comment;
	}

	public CommentSend setComment(String comment) {
		this.comment = comment;
		return this;
	}
}
