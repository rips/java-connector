package com.ripstech.apiconnector2.service.quota;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.quota.Acl;
import com.ripstech.apiconnector2.entity.send.quota.AclSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class QuotaAclService extends PatchDeletePostGetService<Acl, AclSend> {

	private final int quotaId;

	public QuotaAclService(String baseUri, int quotaId) {
		super(baseUri);
		this.quotaId = quotaId;
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
		return String.format("quotas/%d/acls", quotaId);
	}

}
