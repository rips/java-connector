package com.ripstech.apiconnector2.connector.path;

import com.ripstech.apiconnector2.connector.Path;
import com.ripstech.apiconnector2.service.quota.QuotaAclService;

public class QuotaPath extends Path {

	private final int quotaId;

	public QuotaPath(String baseUri, int quotaId) {
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
