package com.ripstech.apiconnector2.entity.send.user.ui;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "reset")
public class ResetSend {

	private String username;
	private String email;

	public ResetSend createPost(String username, String email) {
		return new ResetSend().setUsername(username).setEmail(email);
	}

	private ResetSend() {}

	public String getUsername() {
		return username;
	}

	public ResetSend setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public ResetSend setEmail(String email) {
		this.email = email;
		return this;
	}
}
