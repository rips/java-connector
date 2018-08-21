package com.ripstech.apiconnector2.entity.receive.application.scan.issue;

public class ReviewType {

	private long id;
	private String tag;
	private String name;
	private Boolean negative;

	public long getId() {
		return this.id;
	}

	public String getTag() {
		return this.tag;
	}

	public String getName() {
		return this.name;
	}

	public Boolean getNegative() {
		return this.negative;
	}
}
