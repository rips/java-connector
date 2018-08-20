package com.ripstech.apiconnector2.entity.send;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;
import java.util.Optional;

@JsonRootName(value = "team")
public class TeamSend {

	private Optional<String> name;
	private Optional<List<Long>> users;
	private Optional<Long> organisation;

	public static TeamSend createPost(String name) {
		return new TeamSend().setName(name);
	}

	public static TeamSend createPatch() {
		return new TeamSend();
	}

	private TeamSend() {}

	public TeamSend setName(String name) {
		this.name = Optional.ofNullable(name);
		return this;
	}

	public TeamSend setUsers(List<Long> users) {
		this.users = Optional.ofNullable(users);
		return this;
	}

	public TeamSend setOrganisation(Long organisation) {
		this.organisation = Optional.ofNullable(organisation);
		return this;
	}

	public Optional<String> getName() {
		return this.name;
	}

	public Optional<List<Long>> getUsers() {
		return this.users;
	}

	public Optional<Long> getOrganisation() {
		return this.organisation;
	}
}
