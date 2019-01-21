package com.ripstech.api.connector.service.quota;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.PatchDeletePostGetService;
import com.ripstech.api.entity.receive.quota.Acl;
import com.ripstech.api.entity.send.quota.AclSend;

import java.util.List;

public class QuotaAclService extends PatchDeletePostGetService<Acl, AclSend> {

	private final long quotaId;

	public QuotaAclService(String baseUri, long quotaId) {
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
