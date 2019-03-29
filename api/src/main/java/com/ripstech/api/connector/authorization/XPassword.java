package com.ripstech.api.connector.authorization;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class XPassword extends HeaderAuthenticator {

	private static final String HEADER_EMAIL = "X-Api-Email";
	private static final String HEADER_PASSWORD = "X-Api-Password";
	private static final String HEADER_EMAIL_ENCODED = "X-Api-Email-Enc";
	private static final String HEADER_PASSWORD_ENCODED = "X-Api-Password-Enc";

	private final String email;
	private final String password;

	public XPassword(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public Map<String, String> getAuthHeader() {
		HashMap<String, String> header = new HashMap<>();
		header.put(HEADER_EMAIL_ENCODED, base64Encode(email));
		header.put(HEADER_PASSWORD_ENCODED, base64Encode(password));
		if(isAsciiPrintable(email) && isAsciiPrintable(password)) {
			header.put(HEADER_EMAIL, email);
			header.put(HEADER_PASSWORD, password);
		}
		return Collections.unmodifiableMap(header);
	}

	private String base64Encode(String s) {
		return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
	}

	private boolean isAsciiPrintable(String s) {
		return s.chars().noneMatch(c -> c < ' ' || c > '~');
	}
}
