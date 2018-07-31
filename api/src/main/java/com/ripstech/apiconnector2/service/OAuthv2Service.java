package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.annotation.AuthRequired;
import com.ripstech.apiconnector2.entity.receive.Token;
import com.ripstech.apiconnector2.entity.send.ClientIdSend;
import com.ripstech.apiconnector2.service.template.GenericService;

import java.util.Map;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;
import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.POST;

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

}
