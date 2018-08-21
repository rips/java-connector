package com.ripstech.apiconnector2;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.InputStream;

public class AssumePropertyFileCondition implements ExecutionCondition {
	@Override
	public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
		InputStream inputStream = AssumePropertyFileCondition.class.getClassLoader()
				                          .getResourceAsStream("config.properties");
		if(inputStream == null) {
			return ConditionEvaluationResult.disabled("no property file exists");
		}
		return ConditionEvaluationResult.enabled("");
	}
}
