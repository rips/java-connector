package com.ripstech.apiconnector2.entity.receive;

import java.time.OffsetDateTime;

public class Activity {

	private long id;
	private String type;
	private Object context;
	private Application application;
	private User createdBy;
	private OffsetDateTime createdAt;

	public long getId() {
		return this.id;
	}

	public String getType() {
		return this.type;
	}

	public Object getContext() {
		return this.context;
	}

	public Application getApplication() {
		return this.application;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

	public OffsetDateTime getCreatedAt() {
		return this.createdAt;
	}

}
