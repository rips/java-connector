package com.ripstech.apiconnector2.entity.send.filter.expression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.send.filter.Expression;

import java.util.HashMap;
import java.util.Map;

public class GreaterThanEqual implements Expression {

	@JsonProperty
	private final Map<String, Object> __greaterThanEqual = new HashMap<>(1);

	public GreaterThanEqual(String key, String value) {
		__greaterThanEqual.put(key, value);
	}

	public GreaterThanEqual(String key, long value) {
		__greaterThanEqual.put(key, value);
	}

}
