package com.ripstech.api.connector.service.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.SimpleGetListService;
import com.ripstech.api.entity.receive.application.Acl;

import java.util.List;

public class AclOwnService extends SimpleGetListService<Acl> {

	public AclOwnService(String baseUri) {
		super(baseUri);
	}

	@Override
	public TypeReference<List<Acl>> getGenericListType() {
		return new TypeReference<List<Acl>>() {};
	}

	@Override
	protected String getPath() {
		return "/applications/acls/own";
	}

}
