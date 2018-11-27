package com.ripstech.apiconnector2.entity.receive.callback;

import com.ripstech.api.entity.receive.application.scan.issue.Comment;

public class CommentCallback extends CallbackMessage {

	private Comment item;

	public void setItem(Comment item) {
		this.item = item;
	}

	public Comment getItem() {
		return item;
	}

}
