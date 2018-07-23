package com.ripstech.apiconnector2.service.queryparameter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ripstech.apiconnector2.entity.send.filter.Expression;
import com.ripstech.apiconnector2.entity.send.filter.condition.And;
import com.ripstech.apiconnector2.entity.send.filter.condition.Or;
import com.ripstech.apiconnector2.entity.send.filter.expression.*;

import java.util.List;

public class JsonFilter {

	private final String json;

	public JsonFilter(Expression expression) {
		String filter;
		final ObjectMapper objectMapper = new ObjectMapper();
		try {
			filter = objectMapper.writeValueAsString(expression);
		} catch (JsonProcessingException e) {
			filter = "{}";
			e.printStackTrace();
		}
		json = filter;
	}

	public static And and(Expression... expression) {
		return new And(expression);
	}

	public static And and(List<? extends Expression> expression) {
		return new And(expression);
	}

	public static Or or(Expression... expression) {
		return new Or(expression);
	}

	public static Or or(List<? extends Expression> expression) {
		return new Or(expression);
	}

	public static Equal equal(String key, String value) {
		return new Equal(key, value);
	}

	public static Equal equal(String key, long value) {
		return new Equal(key, value);
	}

	public static Equal equal(String key, boolean value) {
		return new Equal(key, value);
	}

	public static NotEqual notEqual(String key, String value) {
		return new NotEqual(key, value);
	}

	public static NotEqual notEqual(String key, long value) {
		return new NotEqual(key, value);
	}

	public static NotEqual notEqual(String key, boolean value) {
		return new NotEqual(key, value);
	}

	public static Null _null(String key) {
		return new Null(key);
	}

	public static NotNull notNull(String key) {
		return new NotNull(key);
	}

	public static Like like(String key, String value) {
		return new Like(key, value);
	}

	public static Like like(String key, long value) {
		return new Like(key, value);
	}

	public static NotLike notLike(String key, String value) {
		return new NotLike(key, value);
	}

	public static NotLike notLike(String key, long value) {
		return new NotLike(key, value);
	}

	public static GreaterThan greaterThan(String key, String value) {
		return new GreaterThan(key, value);
	}

	public static GreaterThan greaterThan(String key, long value) {
		return new GreaterThan(key, value);
	}

	public static GreaterThanEqual greaterThanEqual(String key, String value) {
		return new GreaterThanEqual(key, value);
	}

	public static GreaterThanEqual greaterThanEqual(String key, long value) {
		return new GreaterThanEqual(key, value);
	}

	public static LessThan lessThan(String key, String value) {
		return new LessThan(key, value);
	}

	public static LessThan lessThan(String key, long value) {
		return new LessThan(key, value);
	}

	public static LessThanEqual lessThanEqual(String key, String value) {
		return new LessThanEqual(key, value);
	}

	public static LessThanEqual lessThanEqual(String key, long value) {
		return new LessThanEqual(key, value);
	}

	public String asJsonString() {
		return json;
	}

}
