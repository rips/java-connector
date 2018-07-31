package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.Quota;
import com.ripstech.apiconnector2.entity.send.QuotaSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

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
