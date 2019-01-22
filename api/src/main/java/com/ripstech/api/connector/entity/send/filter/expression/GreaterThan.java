package com.ripstech.api.connector.entity.send.filter.expression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.api.connector.entity.send.filter.Expression;

import java.util.HashMap;
import java.util.Map;

public class GreaterThan implements Expression {

	@JsonProperty
	private final Map<String, Object> __greaterThan = new HashMap<>(1);

	public GreaterThan(String key, String value) {
		__greaterThan.put(key, value);
	}

	public GreaterThan(String key, long value) {
		__greaterThan.put(key, value);
	}

}
