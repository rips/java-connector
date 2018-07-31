package com.ripstech.apiconnector2.entity.send;

import java.util.List;

public class ClientSend {

	private String name;
	private List<String> allowedGrandTypes;
	private List<String> redirectUris;

	public static ClientSend createPost(String name) {
		return new ClientSend().setName(name);
	}

	public static ClientSend createPut(String name) {
		return new ClientSend().setName(name);
	}

	private ClientSend() {}

	public String getName() {
		return name;
	}

	public ClientSend setName(String name) {
		this.name = name;
		return this;
	}

	public List<String> getAllowedGrandTypes() {
		return allowedGrandTypes;
	}

	public ClientSend setAllowedGrandTypes(List<String> allowedGrandTypes) {
		this.allowedGrandTypes = allowedGrandTypes;
		return this;
	}

	public List<String> getRedirectUris() {
		return redirectUris;
	}

	public ClientSend setRedirectUris(List<String> redirectUris) {
		this.redirectUris = redirectUris;
		return this;
	}

}
