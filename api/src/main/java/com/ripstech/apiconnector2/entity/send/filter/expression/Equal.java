package com.ripstech.apiconnector2.entity.send.filter.expression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.send.filter.Expression;

import java.util.HashMap;
import java.util.Map;

public class Equal implements Expression {

	@JsonProperty
	private final Map<String, Object> __equal = new HashMap<>(1);

	public Equal(String key, String value) {
		__equal.put(key, value);

	}

	public Equal(String key, long value) {
		__equal.put(key, value);
	}

	public Equal(String key, boolean value) {
		__equal.put(key, value);
	}


}
