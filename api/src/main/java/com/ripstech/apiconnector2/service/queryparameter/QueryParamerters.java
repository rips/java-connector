package com.ripstech.apiconnector2.service.queryparameter;

import java.util.LinkedHashMap;

public abstract class QueryParamerters {

	final LinkedHashMap<String, String> params;

	QueryParamerters() {
		this.params = new LinkedHashMap<>();
	}

	public LinkedHashMap<String, String> getParams() {
		return params;
	}

}
