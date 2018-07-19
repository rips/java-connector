package com.ripstech.apiconnector2.entity.send.filter.expression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.send.filter.Expression;

import java.util.HashMap;
import java.util.Map;

public class Null implements Expression {

	@JsonProperty
	private final Map<String, String> __null = new HashMap<>(1);

	public Null(String key) {
		__null.put(key, "");
	}

}
