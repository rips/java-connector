package com.ripstech.apiconnector2.entity.receive;

import java.util.Collections;
import java.util.List;

public class Client {

	private long id;
	private String name;
	private String randomId;
	private String secret;
	private List<String> redirectUris = Collections.emptyList();
	private List<String> allowedGrantTypes = Collections.emptyList();

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getRandomId() {
		return this.randomId;
	}

	public String getSecret() {
		return this.secret;
	}

	public List<String> getRedirectUris() {
		return this.redirectUris;
	}

	public List<String> getAllowedGrantTypes() {
		return this.allowedGrantTypes;
	}

}
