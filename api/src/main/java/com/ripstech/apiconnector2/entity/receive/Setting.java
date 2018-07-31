package com.ripstech.apiconnector2.entity.receive;

import java.time.OffsetDateTime;

public class Setting {

	private String key;
	private String value;
	private OffsetDateTime createdAt;

	public String getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}

	public OffsetDateTime getCreatedAt() {
		return this.createdAt;
	}

}
