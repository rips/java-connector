package com.ripstech.api.connector.connector;

import com.ripstech.api.connector.config.HttpClientConfig;
import com.ripstech.api.connector.service.template.GenericService;

public abstract class Path {

	protected HttpClientConfig httpClientConfig;
	protected final String baseUri;

	protected Path(String baseUri) {
		this.baseUri = baseUri;
	}

	public void setHttpClientConfig(HttpClientConfig httpClientConfig) {
		this.httpClientConfig = httpClientConfig;
	}

	protected void setPrefs(GenericService service) {
		service.setHttpClientConfig(httpClientConfig);
	}

}
