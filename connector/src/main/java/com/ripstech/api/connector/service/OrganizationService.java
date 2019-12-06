package com.ripstech.api.connector.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.PatchDeletePostGetService;
import com.ripstech.api.entity.receive.Organization;
import com.ripstech.api.entity.send.OrganizationSend;

import java.util.List;

public class OrganizationService extends PatchDeletePostGetService<Organization, OrganizationSend> {

	public OrganizationService(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "organizations";
	}

	@Override
	public TypeReference<Organization> getGenericType() {
		return new TypeReference<Organization>() {};
	}

	@Override
	public TypeReference<List<Organization>> getGenericListType() {
		return new TypeReference<List<Organization>>() {};
	}
}
