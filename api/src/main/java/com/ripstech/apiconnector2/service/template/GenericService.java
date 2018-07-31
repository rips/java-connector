package com.ripstech.apiconnector2.service.template;

import com.ripstech.apiconnector2.ApiRequest;
import com.ripstech.apiconnector2.annotation.AuthRequired;
import com.ripstech.apiconnector2.config.Configuration;
import com.ripstech.apiconnector2.config.Configuration.MAPPER_CONFIG;
import com.ripstech.apiconnector2.config.HttpClientConfig;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.*;

public abstract class GenericService {

	protected boolean withRootName = true;
	private boolean authRequired = true;
	protected final String baseUri;
	protected HttpClientConfig httpClientConfig;

	protected GenericService(String baseUri) {
		this.baseUri = baseUri;
	}

	public void setHttpClientConfig(HttpClientConfig httpClientConfig) {
		this.httpClientConfig = httpClientConfig;
	}

	protected abstract String getPath();

	protected ApiRequest getTarget(HttpMethod method) {
		return getTarget(method, withRootName);
	}

	protected ApiRequest getTarget(HttpMethod method, boolean withRootName) {
		switch (method) {
			case GET:
				return getTarget(MAPPER_CONFIG.DEFAULT);
			case DELETE:
				return getTarget(MAPPER_CONFIG.DEFAULT).setMethod(DELETE);
			case POST:
				return withRootName ?
				       getTarget(MAPPER_CONFIG.POST).setMethod(POST) :
				       getTarget(MAPPER_CONFIG.POST_WITHOUT_ROOT_NAME).setMethod(POST);
			case PUT:
				return getTarget(MAPPER_CONFIG.POST).setMethod(PUT);
			case PATCH:
				return withRootName ?
				       getTarget(MAPPER_CONFIG.PATCH).setMethod(PATCH) :
                       getTarget(MAPPER_CONFIG.PATCH_WITHOUT_ROOT_NAME).setMethod(PATCH);
		}
		return getTarget(MAPPER_CONFIG.DEFAULT);
	}

	private ApiRequest getTarget(MAPPER_CONFIG configType) {
		ApiRequest apiRequest = new ApiRequest()
				       .setObjectMapper(Configuration.getObjectMapper(configType))
				       .setBaseUrl(baseUri)
				       .setPath(getPath());
		if(httpClientConfig == null) {
			httpClientConfig = new HttpClientConfig();
		}
		AuthRequired declaredAnnotation = this.getClass().getDeclaredAnnotation(AuthRequired.class);
		if(declaredAnnotation != null) {
			authRequired = declaredAnnotation.value();
		}
		return apiRequest.setClient(httpClientConfig.getClient())
				.setSendAuthHeader(authRequired);
	}

	public enum HttpMethod {
		GET,
		POST,
		PATCH,
		PUT,
		DELETE
	}

}
