package com.ripstech.apiconnector2.entity.receive;

import java.time.Duration;

public class Token {

	private String tokenType;
	private Duration expiresIn;
	private String accessToken;
	private String refreshToken;

	public String getTokenType() {
		return this.tokenType;
	}

	public Duration getExpiresIn() {
		return this.expiresIn;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public String getRefreshToken() {
		return this.refreshToken;
	}

}
