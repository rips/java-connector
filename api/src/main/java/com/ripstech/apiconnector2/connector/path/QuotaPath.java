package com.ripstech.apiconnector2.connector.path;

import com.ripstech.apiconnector2.connector.Path;
import com.ripstech.apiconnector2.service.quota.QuotaAclService;

public class QuotaPath extends Path {

	private final long quotaId;

	public QuotaPath(String baseUri, long quotaId) {
		super(baseUri);
		this.quotaId = quotaId;
	}

	@SuppressWarnings("unused")
	public QuotaAclService acls() {
		QuotaAclService service = new QuotaAclService(baseUri, quotaId);
		setPrefs(service);
		return service;
	}

}
