package com.ripstech.api.connector.entity.send.filter.expression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.api.connector.entity.send.filter.Expression;

import java.util.HashMap;
import java.util.Map;

public class NotNull implements Expression {

	@JsonProperty
	private final Map<String, String> __notNull = new HashMap<>(1);

	public NotNull(String key) {
		__notNull.put(key, "");
	}

}
