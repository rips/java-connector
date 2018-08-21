package com.ripstech.apiconnector2.entity.receive;

import java.time.OffsetDateTime;

public class Log {

	private long id;
	private String text;
	private Integer level;
	private String channel;
	private String requestUri;
	private String requestMethod;
	private String ip;
	private User user;
	private String username;
	private Organisation organisation;
	private String organisationName;
	private String context;
	private OffsetDateTime created;

	public long getId() {
		return this.id;
	}

	public String getText() {
		return this.text;
	}

	public Integer getLevel() {
		return this.level;
	}

	public String getChannel() {
		return this.channel;
	}

	public String getRequestUri() {
		return this.requestUri;
	}

	public String getRequestMethod() {
		return this.requestMethod;
	}

	public String getIp() {
		return this.ip;
	}

	public User getUser() {
		return this.user;
	}

	public String getUsername() {
		return this.username;
	}

	public Organisation getOrganisation() {
		return this.organisation;
	}

	public String getOrganisationName() {
		return this.organisationName;
	}

	public String getContext() {
		return this.context;
	}

	public OffsetDateTime getCreated() {
		return this.created;
	}

}
