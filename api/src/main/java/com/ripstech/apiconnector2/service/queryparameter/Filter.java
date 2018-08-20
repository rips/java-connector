package com.ripstech.apiconnector2.service.queryparameter;

import com.ripstech.apiconnector2.entity.send.filter.Expression;

import java.time.OffsetDateTime;

public class Filter extends QueryParamerters {

	public static Filter empty() {
		return new Filter();
	}

	public Filter limit(long i) {
		and("limit", Long.toString(i));
		return this;
	}

	public Filter offset(long i) {
		and("offset", Long.toString(i));
		return this;
	}

	public Filter orderBy(String name, Order value) {
		and("orderBy", name, value.name().toLowerCase());
		return this;
	}

	public Filter isEqual(String name, String value) {
		and("equal", name, value);
		return this;
	}

	public Filter isEqual(String name, long value) {
		isEqual(name, Long.toString(value));
		return this;
	}

	public Filter isEqual(String name, OffsetDateTime value) {
		isEqual(name, value.toString());
		return this;
	}

	public Filter notEqual(String name, String value) {
		and("notEqual", name, value);
		return this;
	}

	public Filter notEqual(String name, long value) {
		notEqual(name, Long.toString(value));
		return this;
	}

	public Filter notEqual(String name, OffsetDateTime value) {
		notEqual(name, value.toString());
		return this;
	}

	public Filter nul(String name, String value) {
		and("null", name, value);
		return this;
	}

	public Filter notNull(String name, String value) {
		and("notNull", name, value);
		return this;
	}

	public Filter like(String name, String value) {
		and("like", name, value);
		return this;
	}

	public Filter notLike(String name, String value) {
		and("notLike", name, value);
		return this;
	}

	public Filter lessThan(String name, String value) {
		and("lessThan", name, value);
		return this;
	}

	public Filter lessThan(String name, OffsetDateTime value) {
		and("lessThan", name, value.toString());
		return this;
	}

	public Filter lessThan(String name, long value) {
		and("lessThan", name, String.valueOf(value));
		return this;
	}

	public Filter greaterThan(String name, String value) {
		and("greaterThan", name, value);
		return this;
	}

	public Filter greaterThan(String name, OffsetDateTime value) {
		and("greaterThan", name, value.toString());
		return this;
	}

	public Filter greaterThan(String name, long value) {
		and("greaterThan", name, String.valueOf(value));
		return this;
	}

	public Filter lessThanEqual(String name, String value) {
		and("lessThanEqual", name, value);
		return this;
	}

	public Filter lessThanEqual(String name, OffsetDateTime value) {
		and("lessThanEqual", name, value.toString());
		return this;
	}


	public Filter lessThanEqual(String name, long value) {
		and("lessThanEqual", name, String.valueOf(value));
		return this;
	}

	public Filter greaterThanEqual(String name, String value) {
		and("greaterThanEqual", name, value);
		return this;
	}

	public Filter greaterThanEqual(String name, OffsetDateTime value) {
		and("greaterThanEqual", name, value.toString());
		return this;
	}

	public Filter greaterThanEqual(String name, long value) {
		and("greaterThanEqual", name, String.valueOf(value));
		return this;
	}

	public Filter readable() {
		params.put("showIssueReadable", "1");
		return this;
	}

	public Filter json(Expression expression) {
		json(new JsonFilter(expression));
		return this;
	}

	public Filter json(JsonFilter jsonFilter) {
		params.put("filter", jsonFilter.asJsonString());
		return this;
	}

	public int countFilters() {
		return params.size();
	}

	private void and(String operator, String value) {
		params.put(operator, value);
	}

	private void and(String operator, String name, String value) {
		params.put(String.format("%s[%s]", operator, name), value);
	}

	public enum Order {
		ASC,
		DESC
	}

}
