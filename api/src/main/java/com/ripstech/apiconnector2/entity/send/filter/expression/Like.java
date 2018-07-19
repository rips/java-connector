package com.ripstech.apiconnector2.entity.send.filter.expression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.send.filter.Expression;

import java.util.HashMap;
import java.util.Map;

public class Like implements Expression {

	@JsonProperty
	private final Map<String, Object> __like = new HashMap<>(1);

	public Like(String key, String value) {
		__like.put(key, value);
	}

	public Like(String key, long value) {
		__like.put(key, value);
	}

}
