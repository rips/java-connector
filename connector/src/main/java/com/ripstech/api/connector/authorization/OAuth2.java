package com.ripstech.api.connector.authorization;

import com.ripstech.api.connector.config.HttpClientConfig;
import com.ripstech.api.connector.entity.receive.Token;
import com.ripstech.api.connector.entity.send.ClientIdSend;
import com.ripstech.api.connector.exception.ApiException;
import com.ripstech.api.connector.exception.ClientIdMissingException;
import com.ripstech.api.connector.service.OAuthv2Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

public class OAuth2 extends HeaderAuthenticator {

	private String accessToken;
	private String refreshToken;
	private Duration expiresIn;
	private String clientId;

	public OAuth2(String accessToken) {
		this.accessToken = accessToken;
	}

	public OAuth2(String baseUri, String email, String password, HttpClientConfig httpClientConfig) throws ApiException {
		this(baseUri, email, password, "ci", httpClientConfig);
	}

	public OAuth2(String baseUri, String email, String password, String clientIdName, HttpClientConfig httpClientConfig) throws ApiException {
		this(baseUri, email, password, clientIdName, httpClientConfig, false);
	}

	public OAuth2(String baseUri, String email, String password, String clientIdName, HttpClientConfig httpClientConfig, boolean doNotFetchClientId) throws ApiException {
		OAuthv2Service oAuthv2Service = new OAuthv2Service(baseUri);
		oAuthv2Service.setHttpClientConfig(httpClientConfig);
		Map<String, String> clientIds = oAuthv2Service.getGlobalClientIds().orThrow(ApiException::new);
		if(doNotFetchClientId) {
			clientId = clientIdName;
		} else {
			if (!clientIds.containsKey(clientIdName)) {
				throw new ClientIdMissingException("No client ID with name: " + clientIdName);
			}
			clientId = clientIds.get(clientIdName);
		}
		ClientIdSend postBody = ClientIdSend.toPost(clientId, email, password);
		Token token = oAuthv2Service.getAuthToken(postBody).orThrow(ApiException::new);
		accessToken = token.getAccessToken();
		refreshToken = token.getRefreshToken();
		expiresIn = token.getExpiresIn();
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public Duration getExpiresIn() {
		return expiresIn;
	}

	public String getClientId() {
		return clientId;
	}

	@Override
	public Map<String, String> getAuthHeader() {
		return Collections.singletonMap("Authorization", "Bearer " + accessToken);
	}
}