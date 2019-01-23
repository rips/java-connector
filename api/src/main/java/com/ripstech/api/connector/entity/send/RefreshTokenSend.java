package com.ripstech.api.connector.entity.send;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RefreshTokenSend {

	private String clientId;
	private String refreshToken;
	@JsonProperty("grantType")
	private static final String GRANT_TYPE = "refresh_token";

	private RefreshTokenSend() {}

	public static RefreshTokenSend toPost(String clientId, String refreshToken) {
		return new RefreshTokenSend().setClientId(clientId).setRefreshToken(refreshToken);
	}

	public String getClientId() {
		return clientId;
	}

	public RefreshTokenSend setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public RefreshTokenSend setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
		return this;
	}

	public String getGrantType() {
		return GRANT_TYPE;
	}
}
