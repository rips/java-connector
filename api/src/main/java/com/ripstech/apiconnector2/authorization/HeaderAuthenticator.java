package com.ripstech.apiconnector2.authorization;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import java.util.Map;
import java.util.Objects;

public abstract class HeaderAuthenticator implements Authenticator {

	public abstract Map<String, String> getAuthHeader();

	@Override
	public Request authenticate(Route route, Response response) {
		if(getAuthHeader().keySet().stream().map(response.request()::header).noneMatch(Objects::isNull)) {
			return null;
		}
		Request.Builder builder = response.request().newBuilder();
		getAuthHeader().forEach(builder::header);
		return builder.build();
	}

}
