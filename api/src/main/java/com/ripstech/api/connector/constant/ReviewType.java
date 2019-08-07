package com.ripstech.api.connector.constant;

@SuppressWarnings("unused")
public enum ReviewType {

	NOT_REVIEWED(1L, "Not reviewed", false),
	EXPLOITABLE(2L, "Exploitable", false),
	SUSPICIOUS(3L, "Suspicious", false),
	BAD_PRACTICE(4L, "Bad practice", false),
	NOT_SURE(5L, "Not sure", false),
	IN_PROGRESS(6L, "In progress", false),
	FIXED(7L, "Fixed", false),
	NOT_EXPLOITABLE(8L, "Not exploitable", true),
	NO_ISSUE(9L, "Not an issue", true),
	DUPLICATE(10L, "Duplicate", true);

	private final long id;
	private final String name;
	private final boolean negative;

	ReviewType(long id, String name, boolean negative) {
		this.id = id;
		this.name = name;
		this.negative = negative;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTag() {
		return name().toUpperCase();
	}

	public boolean isNegative() {
		return negative;
	}
}
