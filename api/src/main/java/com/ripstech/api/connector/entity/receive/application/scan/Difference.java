package com.ripstech.api.connector.entity.receive.application.scan;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Difference {

	private int old;
	@JsonProperty("new")
	private int new_;
	private int fixed;

	public int getNew_() {
		return new_;
	}

	public int getOld() {
		return this.old;
	}

	public int getFixed() {
		return this.fixed;
	}

}
