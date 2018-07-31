package com.ripstech.apiconnector2.entity.send;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;
import java.util.Optional;

@JsonRootName(value = "send")
public class UserSend {

	private Optional<String> plainPassword;
	private Optional<String> email;
	private Optional<String> firstname;
	private Optional<String> lastname;
	private Optional<List<String>> roles;
	private Optional<String> username;
	private Optional<Boolean> enabled;
	private Optional<Integer> organisation;
	private Optional<Integer> chargedQuota;
	private Optional<List<String>> whitelistedIps;

	public static UserSend createPost(String plainPassword, String email) {
		return new UserSend().setPlainPassword(plainPassword).setEmail(email);
	}

	public static UserSend createPatch() {
		return new UserSend();
	}

	private UserSend() {}

	public UserSend setPlainPassword(String plainPassword) {
		this.plainPassword = Optional.ofNullable(plainPassword);
		return this;
	}

	public UserSend setEmail(String email) {
		this.email = Optional.ofNullable(email);
		return this;
	}

	public UserSend setFirstname(String firstname) {
		this.firstname = Optional.ofNullable(firstname);
		return this;
	}

	public UserSend setLastname(String lastname) {
		this.lastname = Optional.ofNullable(lastname);
		return this;
	}

	public UserSend setRoles(List<String> roles) {
		this.roles = Optional.ofNullable(roles);
		return this;
	}

	public UserSend setUsername(String username) {
		this.username = Optional.ofNullable(username);
		return this;
	}

	public UserSend setEnabled(Boolean enabled) {
		this.enabled = Optional.ofNullable(enabled);
		return this;
	}

	public UserSend setOrganisation(Integer organisation) {
		this.organisation = Optional.ofNullable(organisation);
		return this;
	}

	public UserSend setChargedQuota(Integer chargedQuota) {
		this.chargedQuota = Optional.ofNullable(chargedQuota);
		return this;
	}

	public UserSend setWhitelistedIps(List<String> whitelistedIps) {
		this.whitelistedIps = Optional.ofNullable(whitelistedIps);
		return this;
	}

	public Optional<String> getPlainPassword() {
		return this.plainPassword;
	}

	public Optional<String> getEmail() {
		return this.email;
	}

	public Optional<String> getFirstname() {
		return this.firstname;
	}

	public Optional<String> getLastname() {
		return this.lastname;
	}

	public Optional<List<String>> getRoles() {
		return this.roles;
	}

	public Optional<String> getUsername() {
		return this.username;
	}

	public Optional<Boolean> getEnabled() {
		return this.enabled;
	}

	public Optional<Integer> getOrganisation() {
		return this.organisation;
	}

	public Optional<Integer> getChargedQuota() {
		return this.chargedQuota;
	}

	public Optional<List<String>> getWhitelistedIps() {
		return this.whitelistedIps;
	}
}
