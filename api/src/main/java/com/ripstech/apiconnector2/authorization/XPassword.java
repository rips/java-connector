package com.ripstech.apiconnector2.authorization;

import java.util.HashMap;
import java.util.Map;

public class XPassword extends HeaderAuthenticator {

	private static final String HEADER_EMAIL = "X-Api-Email";
	private static final String HEADER_PASSWORD = "X-Api-Password";

	private final String email;
	private final String password;

	public XPassword(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public Map<String, String> getAuthHeader() {
		return new HashMap<String, String>() {{
				put(HEADER_EMAIL, email);
				put(HEADER_PASSWORD, password);
		}};
	}
}
