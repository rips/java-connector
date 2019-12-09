package com.ripstech.api.connector.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.PatchDeletePostGetService;
import com.ripstech.api.entity.receive.Quota;
import com.ripstech.api.entity.send.QuotaSend;

import java.util.List;

public class QuotaService extends PatchDeletePostGetService<Quota, QuotaSend> {

	public QuotaService(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "quotas";
	}

	@Override
	public TypeReference<Quota> getGenericType() {
		return new TypeReference<Quota>() {};
	}

	@Override
	public TypeReference<List<Quota>> getGenericListType() {
		return new TypeReference<List<Quota>>() {};
	}

}
