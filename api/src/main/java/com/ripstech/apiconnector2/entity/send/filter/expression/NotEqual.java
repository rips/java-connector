package com.ripstech.apiconnector2.entity.send.filter.expression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.send.filter.Expression;

import java.util.HashMap;
import java.util.Map;

public class NotEqual implements Expression {

	@JsonProperty
	private final Map<String, Object> __notEqual = new HashMap<>(1);

	public NotEqual(String key, String value) {
		__notEqual.put(key, value);
	}

	public NotEqual(String key, long value) {
		__notEqual.put(key, value);
	}

	public NotEqual(String key, boolean value) {
		__notEqual.put(key, value);
	}

}
