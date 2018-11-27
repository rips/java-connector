package com.ripstech.apiconnector2.entity.receive.callback;

import com.ripstech.api.entity.receive.application.Scan;

public class ScanCallback extends CallbackMessage {

	private Scan item;

	public void setItem(Scan item) {
		this.item = item;
	}

	public Scan getItem() {
		return item;
	}

}
