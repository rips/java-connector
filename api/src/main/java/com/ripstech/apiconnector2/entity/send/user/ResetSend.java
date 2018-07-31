package com.ripstech.apiconnector2.entity.send.user;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "user")
public class ResetSend {

	private String plainPassword;

	public static ResetSend createPost(String plainPassword) {
		return new ResetSend().setPlainPassword(plainPassword);
	}

	private ResetSend() {}

	public String getPlainPassword() {
		return plainPassword;
	}

	public ResetSend setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
		return this;
	}
}
