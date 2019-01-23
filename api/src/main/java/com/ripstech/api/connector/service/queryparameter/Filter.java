package com.ripstech.api.connector.service.queryparameter;

import com.ripstech.api.connector.entity.send.filter.Expression;

import java.util.*;
import java.util.stream.Collectors;

public class Filter extends QueryParamerters {

	private Set<String> selects = new HashSet<>();
	private List<OrderBy> orders = new ArrayList<>();

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

	public Filter orderBy(String column, Order order) {
		this.orders.add(new OrderBy(column, order));
		return this;
	}

	public Filter orderBy(OrderBy... orders) {
		this.orders.addAll(Arrays.asList(orders));
		return this;
	}

	public Filter orderBy(String... orders) {
		Arrays.stream(orders).map(OrderBy::new).forEach(this.orders::add);
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
		params.put("customFilter", "{\"readable\": {\"show\": true}}");
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
		if(orders.size() > 0) {
			and("orderBy",
			    orders.stream()
					    .map(Object::toString)
					    .collect(Collectors.joining(
					    		",",
							    "{",
							    "}")));
		}
	}

	public enum Order {
		ASC,
		DESC
	}

	public class OrderBy {
		private String columnName;
		private Order order = Order.ASC;

		public OrderBy(String columnName) {
			this.columnName = columnName;
		}

		public OrderBy(String columnName, Order order) {
			this.columnName = columnName;
			this.order = order;
		}

		@Override
		public String toString() {
			return String.format("\"%s\":\"%s\"", columnName, order.name().toLowerCase());
		}
	}

}
