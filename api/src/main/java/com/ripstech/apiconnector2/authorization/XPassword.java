package com.ripstech.apiconnector2.authorization;

import java.util.HashMap;
import java.util.Map;

public class XPassword extends HeaderAuthenticator {

	private static final String HEADER_USERNAME = "X-Api-Username";
	private static final String HEADER_PASSWORD = "X-Api-Password";

	private final String username;
	private final String password;

	public XPassword(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public Map<String, String> getAuthHeader() {
		return new HashMap<String, String>() {{
				put(HEADER_USERNAME, username);
				put(HEADER_PASSWORD, password);
		}};
	}
}
