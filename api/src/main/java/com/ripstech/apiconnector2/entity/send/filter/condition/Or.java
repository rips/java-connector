package com.ripstech.apiconnector2.entity.send.filter.condition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.send.filter.Condition;
import com.ripstech.apiconnector2.entity.send.filter.Expression;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Or implements Condition, Expression {

	@JsonProperty
	private final List<Expression> __or = new LinkedList<>();

	public Or(Expression... expressions) {
		__or.addAll(Arrays.asList(expressions));
	}

}
