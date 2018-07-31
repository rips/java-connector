package com.ripstech.apiconnector2.service.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.Acl;
import com.ripstech.apiconnector2.service.template.SimpleGetListService;

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
