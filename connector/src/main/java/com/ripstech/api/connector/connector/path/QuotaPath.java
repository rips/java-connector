package com.ripstech.api.connector.connector.path;

import com.ripstech.api.connector.service.quota.QuotaAclService;
import com.ripstech.api.connector.connector.Path;

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
