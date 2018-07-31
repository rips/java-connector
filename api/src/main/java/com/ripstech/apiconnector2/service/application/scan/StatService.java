package com.ripstech.apiconnector2.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.scan.Stats;
import com.ripstech.apiconnector2.service.template.SimpleGetService;

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
