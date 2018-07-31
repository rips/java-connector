package com.ripstech.apiconnector2.service.queryparameter;

import java.util.HashMap;
import java.util.Map;

public abstract class QueryParamerters {

	final Map<String, String> params;

	QueryParamerters() {
		this.params = new HashMap<>();
	}

	public Map<String, String> getParams() {
		return params;
	}

}
