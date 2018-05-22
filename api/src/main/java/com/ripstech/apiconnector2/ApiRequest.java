package com.ripstech.apiconnector2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ripstech.apiconnector2.authorization.HeaderAuthenticator;
import com.ripstech.apiconnector2.service.queryparameter.QueryParamerters;
import com.ripstech.apiconnector2.service.template.GenericService.HttpMethod;
import okhttp3.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Objects;

public class ApiRequest {

	static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
	private ObjectMapper objectMapper;
	private String baseUrl;
	private String path;
	private LinkedHashMap<String, String> queryParams = new LinkedHashMap<>();
	private HttpMethod method = HttpMethod.GET;
	private RequestBody body;
	private JsonProcessingException exception;
	private String acceptHeader = Objects.requireNonNull(MEDIA_TYPE_JSON).toString();
	private OkHttpClient client = new OkHttpClient();
	private boolean sendAuthHeader = true;

	ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public ApiRequest setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		return this;
	}

	public ApiRequest setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
		return this;
	}

	public ApiRequest setPath(String path) {
		this.path = path;
		return this;
	}

	public ApiRequest appendPath(String path) {
		this.path += "/" + path;
		return this;
	}

	public ApiRequest appendPath(int path) {
		this.path += "/" + path;
		return this;
	}

	public ApiRequest setMethod(HttpMethod method) {
		this.method = method;
		return this;
	}

	public ApiRequest addQueryParam(String key, boolean value) {
		this.queryParams.put(key, String.valueOf(value));
		return this;
	}

	public ApiRequest addQueryParam(String key, String value) {
		this.queryParams.put(key, value);
		return this;
	}

	public ApiRequest addQueryParams(QueryParamerters queryParams) {
		this.queryParams.putAll(queryParams.getParams());
		return this;
	}

	public ApiRequest setQueryParams(QueryParamerters queryParams) {
		this.queryParams = queryParams.getParams();
		return this;
	}

	public ApiRequest setQueryParams(LinkedHashMap<String, String> queryParams) {
		this.queryParams = queryParams;
		return this;
	}

	public ApiRequest setBody(RequestBody body) {
		this.body = body;
		return this;
	}

	public ApiRequest setJsonBody(Object obj) {
		try {
			this.body = RequestBody.create(MEDIA_TYPE_JSON,
			                               objectMapper
					                               .setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
					                               .writeValueAsString(obj));
		} catch (JsonProcessingException e) {
			exception = e;
		}
		return this;
	}

	public ApiRequest setAcceptHeader(String acceptHeader) {
		this.acceptHeader = acceptHeader;
		return this;
	}

	public ApiRequest setClient(OkHttpClient okHttpClient) {
		this.client = okHttpClient;
		return this;
	}

	public ApiRequest setSendAuthHeader(boolean sendAuthHeader) {
		this.sendAuthHeader = sendAuthHeader;
		return this;
	}

	private Request.Builder builder() {
		if(path.startsWith("/")) {
			path = path.substring(1, path.length());
		}
		HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl)
				                          .newBuilder()
				                          .addPathSegments(path);
		queryParams.forEach(urlBuilder::addQueryParameter);
		Request.Builder request = new Request.Builder()
				                          .url(urlBuilder.build())
				                          .addHeader("Accept", acceptHeader);
		if(sendAuthHeader) {
			Authenticator authenticator = client.authenticator();
			if (authenticator instanceof HeaderAuthenticator) {
				((HeaderAuthenticator) authenticator).getAuthHeader().forEach(request::header);
			}
		}
		return request;
	}

	Response execute() throws IOException {
		if (exception != null)
			throw exception;
		Request.Builder builder = builder();
		switch (method) {
			case GET:
				builder.get();
				break;
			case POST:
				builder.post(body);
				break;
			case DELETE:
				builder.delete();
				break;
			case PUT:
				builder.put(body);
				break;
			case PATCH:
				builder.patch(body);
				break;
		}
		return client.newCall(builder.build())
				       .execute();
	}

}
