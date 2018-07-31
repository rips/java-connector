package com.ripstech.apiconnector2.service.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.Acl;
import com.ripstech.apiconnector2.entity.send.application.AclSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class AclService extends PatchDeletePostGetService<Acl, AclSend> {

	private final int applicationId;

	public AclService(String baseUri, int applicationId) {
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
