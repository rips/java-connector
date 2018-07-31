package com.ripstech.apiconnector2.service.queryparameter;

import java.time.OffsetDateTime;

public class Filter extends QueryParamerters {

	public static Filter empty() {
		return new Filter();
	}

	public Filter limit(int i) {
		and("limit", Integer.toString(i));
		return this;
	}

	public Filter offset(int i) {
		and("offset", Integer.toString(i));
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

	public Filter isEqual(String name, int value) {
		isEqual(name, Integer.toString(value));
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

	public Filter notEqual(String name, int value) {
		notEqual(name, Integer.toString(value));
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

	public Filter lessThan(String name, int value) {
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

	public Filter greaterThan(String name, int value) {
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


	public Filter lessThanEqual(String name, int value) {
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

	public Filter greaterThanEqual(String name, int value) {
		and("greaterThanEqual", name, String.valueOf(value));
		return this;
	}

	public Filter readable() {
		params.put("showIssueReadable", "1");
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
