package com.ripstech.api.connector.service.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.PatchDeletePostGetService;
import com.ripstech.api.entity.receive.application.Acl;
import com.ripstech.api.entity.send.application.AclSend;

import java.util.List;

public class AclService extends PatchDeletePostGetService<Acl, AclSend> {

	private final long applicationId;

	public AclService(String baseUri, long applicationId) {
		super(baseUri);
		this.applicationId = applicationId;
	}

	@Override
	public TypeReference<Acl> getGenericType() {
		return new TypeReference<Acl>() {};
	}

	@Override
	public TypeReference<List<Acl>> getGenericListType() {
		return new TypeReference<List<Acl>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/acls", applicationId);
	}

}
