package com.ripstech.apiconnector2.entity.send.filter.expression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.send.filter.Expression;

import java.util.HashMap;
import java.util.Map;

public class NotLike implements Expression {

	@JsonProperty
	private final Map<String, Object> __notLike = new HashMap<>(1);

	public NotLike(String key, String value) {
		__notLike.put(key, value);
	}

	public NotLike(String key, long value) {
		__notLike.put(key, value);
	}

}
