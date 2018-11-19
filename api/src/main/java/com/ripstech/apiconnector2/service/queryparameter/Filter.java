package com.ripstech.apiconnector2.service.queryparameter;

import com.ripstech.apiconnector2.entity.send.filter.Expression;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Filter extends QueryParamerters {

	private Set<String> selects = new HashSet<>();

	public Filter() {}

	public Filter(Expression expression) {
		this.json(expression);
	}

	public Filter(JsonFilter jsonFilter) {
		this.json(jsonFilter);
	}

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

	public Filter select(String... fields) {
		selects.addAll(Arrays.asList(fields));
		return this;
	}

	public Filter select(Collection<String> fields) {
		selects.addAll(fields);
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

	@Override
	protected void finalizeParameters() {
		if(selects.size() > 0) {
			and("select",
			    selects.stream()
					    .collect(Collectors.joining(
							    "\", \"",
							    "[\"",
							    "\"]")));
		}
	}

	public enum Order {
		ASC,
		DESC
	}

}
