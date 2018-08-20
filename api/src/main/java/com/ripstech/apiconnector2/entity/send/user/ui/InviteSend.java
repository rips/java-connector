package com.ripstech.apiconnector2.entity.send.user.ui;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value = "user")
public class InviteSend {

	private String firstname;
	private String lastname;
	private String email;
	private Long organisation;
	private List<String> roles;

	public static InviteSend createPost(String email, long organisation) {
		return new InviteSend().setEmail(email).setOrganisation(organisation);
	}

	private InviteSend() {}

	public String getFirstname() {
		return firstname;
	}

	public InviteSend setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public String getLastname() {
		return lastname;
	}

	public InviteSend setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public InviteSend setEmail(String email) {
		this.email = email;
		return this;
	}

	public Long getOrganisation() {
		return organisation;
	}

	public InviteSend setOrganisation(Long organisation) {
		this.organisation = organisation;
		return this;
	}

	public List<String> getRoles() {
		return roles;
	}

	public InviteSend setRoles(List<String> roles) {
		this.roles = roles;
		return this;
	}
}
