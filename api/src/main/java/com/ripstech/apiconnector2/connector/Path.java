package com.ripstech.apiconnector2.connector;

import com.ripstech.apiconnector2.config.HttpClientConfig;
import com.ripstech.apiconnector2.service.template.GenericService;

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
