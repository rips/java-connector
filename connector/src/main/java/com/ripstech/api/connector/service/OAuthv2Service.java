package com.ripstech.api.connector.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.ApiResponse;
import com.ripstech.api.connector.annotation.AuthRequired;
import com.ripstech.api.connector.entity.receive.Token;
import com.ripstech.api.connector.entity.send.ClientIdSend;
import com.ripstech.api.connector.entity.send.RefreshTokenSend;
import com.ripstech.api.connector.service.template.GenericService;

import java.util.Map;

import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.GET;
import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.POST;

@AuthRequired(false)
public class OAuthv2Service extends GenericService {

	public OAuthv2Service(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "oauth/v2";
	}

	public ApiResponse<Map<String, String>> getGlobalClientIds() {
		return new ApiResponse<>(getTarget(GET)
				                         .appendPath("global/clients"),
		                         new TypeReference<Map<String, String>>() {});
	}

	public ApiResponse<Token> getAuthToken(ClientIdSend clientId) {
		return new ApiResponse<>(getTarget(POST, false)
				                         .appendPath("auth/tokens")
				                         .setJsonBody(clientId),
		                         Token.class);
	}

	public ApiResponse<Token> getAuthToken(RefreshTokenSend refreshToken) {
		return new ApiResponse<>(getTarget(POST, false)
				                         .appendPath("auth/tokens")
				                         .setJsonBody(refreshToken),
		                         Token.class);
	}

}
