package com.ripstech.api.connector.entity.send.filter.condition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.api.connector.entity.send.filter.Expression;
import com.ripstech.api.connector.entity.send.filter.Condition;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class And implements Condition, Expression {

	@JsonProperty
	private final List<Expression> __and = new LinkedList<>();

	public And(Expression... expressions) {
		__and.addAll(Arrays.asList(expressions));
	}

	public And(List<? extends Expression> expressions) {
		__and.addAll(expressions);
	}

}
