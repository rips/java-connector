package com.ripstech.api.connector.entity.send.filter.expression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.api.connector.entity.send.filter.Expression;

import java.util.HashMap;
import java.util.Map;

public class LessThanEqual implements Expression {

	@JsonProperty
	private final Map<String, Object> __lessThanEqual = new HashMap<>(1);

	public LessThanEqual(String key, String value) {
		__lessThanEqual.put(key, value);
	}

	public LessThanEqual(String key, long value) {
		__lessThanEqual.put(key, value);
	}

}
