package com.ripstech.apiconnector2.entity.receive.application.scan;

import com.ripstech.apiconnector2.type.SourceType;

public class Source extends FlowEdgePoint {

	private String parameter;
	private SourceType type;

	public String getParameter() {
		return this.parameter;
	}

	public SourceType getType() {
		return type;
	}

}
