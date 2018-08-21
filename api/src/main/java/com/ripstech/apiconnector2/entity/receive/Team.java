package com.ripstech.apiconnector2.entity.receive;

import java.util.Collections;
import java.util.List;

public class Team {

	private long id;
	private String name;
	private Organisation organisation;
	private List<User> users = Collections.emptyList();
	private User createdBy;

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Organisation getOrganisation() {
		return this.organisation;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

}
