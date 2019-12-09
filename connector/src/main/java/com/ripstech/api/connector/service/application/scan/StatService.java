package com.ripstech.api.connector.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.application.scan.Stats;
import com.ripstech.api.connector.service.template.SimpleGetService;

public class StatService extends SimpleGetService<Stats> {

	public StatService(String baseUri) {
		super(baseUri);
	}

	@Override
	public TypeReference<Stats> getGenericType() {
		return new TypeReference<Stats>() {};
	}

	@Override
	protected String getPath() {
		return "/applications/scans/stats";
	}


}
