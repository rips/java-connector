package com.ripstech.apiconnector2.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

@SuppressWarnings("unused")
public enum SourceType {

	METHOD_WITHOUT_PARAMETERS(1),
	METHOD_WITH_PARAMETERS(2),
	OBJECT_CREATION_WITHOUT_PARAMETERS(3),
	OBJECT_CREATION_WITH_PARAMETERS(4),
	PARAMETER(5);

	private long apiId;

	@JsonCreator
	SourceType(long apiId) {
		this.apiId = apiId;
	}

	@JsonValue
	public long getApiId() {
		return apiId;
	}

}
