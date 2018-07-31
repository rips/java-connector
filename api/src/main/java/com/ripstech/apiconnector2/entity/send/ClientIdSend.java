package com.ripstech.apiconnector2.entity.send;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ClientIdSend {

	private String clientId;
	private String username;
	private String password;
	@JsonProperty("grantType")
	private static final String GRANT_TYPE = "password";

	public static ClientIdSend toPost(String clientId, String username, String password) {
		return new ClientIdSend().setClientId(clientId).setUsername(username).setPassword(password);
	}

	private ClientIdSend() {}

	public String getClientId() {
		return clientId;
	}

	public ClientIdSend setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public ClientIdSend setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public ClientIdSend setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getGrantType() {
		return GRANT_TYPE;
	}
}
