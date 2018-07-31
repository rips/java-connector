package com.ripstech.apiconnector2.entity.send.user;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "user")
public class ActivateSend {

	private String plainPassword;
	private String username;

	public static ActivateSend createPost(String username, String plainPassword) {
		return new ActivateSend().setUsername(username).setPlainPassword(plainPassword);
	}

	private ActivateSend() {}

	public String getPlainPassword() {
		return plainPassword;
	}

	public ActivateSend setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public ActivateSend setUsername(String username) {
		this.username = username;
		return this;
	}

}
