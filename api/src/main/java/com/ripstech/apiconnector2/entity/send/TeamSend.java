package com.ripstech.apiconnector2.entity.send;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;
import java.util.Optional;

@JsonRootName(value = "team")
public class TeamSend {

	private Optional<String> name;
	private Optional<List<Integer>> users;
	private Optional<Integer> organisation;

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

	public TeamSend setUsers(List<Integer> users) {
		this.users = Optional.ofNullable(users);
		return this;
	}

	public TeamSend setOrganisation(Integer organisation) {
		this.organisation = Optional.ofNullable(organisation);
		return this;
	}

	public Optional<String> getName() {
		return this.name;
	}

	public Optional<List<Integer>> getUsers() {
		return this.users;
	}

	public Optional<Integer> getOrganisation() {
		return this.organisation;
	}
}
