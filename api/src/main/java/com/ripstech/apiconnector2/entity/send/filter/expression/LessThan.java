package com.ripstech.apiconnector2.entity.send.filter.expression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.send.filter.Expression;

import java.util.HashMap;
import java.util.Map;

public class LessThan implements Expression {

	@JsonProperty
	private final Map<String, Object> __lessThan = new HashMap<>(1);

	public LessThan(String key, String value) {
		__lessThan.put(key, value);
	}

	public LessThan(String key, long value) {
		__lessThan.put(key, value);
	}

}
