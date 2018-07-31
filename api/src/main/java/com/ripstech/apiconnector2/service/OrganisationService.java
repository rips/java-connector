package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.Organisation;
import com.ripstech.apiconnector2.entity.send.OrganisationSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class OrganisationService extends PatchDeletePostGetService<Organisation, OrganisationSend> {

	public OrganisationService(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "organisations";
	}

	@Override
	public TypeReference<Organisation> getGenericType() {
		return new TypeReference<Organisation>() {};
	}

	@Override
	public TypeReference<List<Organisation>> getGenericListType() {
		return new TypeReference<List<Organisation>>() {};
	}
}
