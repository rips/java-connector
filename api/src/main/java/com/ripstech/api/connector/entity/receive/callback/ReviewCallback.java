package com.ripstech.api.connector.entity.receive.callback;

import com.ripstech.api.entity.receive.application.scan.issue.Review;

public class ReviewCallback extends CallbackMessage {

	private Review item;

	public void setItem(Review item) {
		this.item = item;
	}

	public Review getItem() {
		return item;
	}

}
