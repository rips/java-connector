package com.ripstech.apiconnector2.entity.send.filter.condition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.send.filter.Condition;
import com.ripstech.apiconnector2.entity.send.filter.Expression;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class And implements Condition, Expression {

	@JsonProperty
	private final List<Expression> __and = new LinkedList<>();

	public And(Expression... expressions) {
		__and.addAll(Arrays.asList(expressions));
	}

}
