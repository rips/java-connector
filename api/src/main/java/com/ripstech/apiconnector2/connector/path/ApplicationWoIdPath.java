package com.ripstech.apiconnector2.connector.path;

import com.ripstech.apiconnector2.connector.Path;
import com.ripstech.apiconnector2.connector.path.application.ScanWoIdPath;
import com.ripstech.apiconnector2.service.application.AclOwnService;

public class ApplicationWoIdPath extends Path {

	public ApplicationWoIdPath(String baseUri) {
		super(baseUri);
	}

	public ScanWoIdPath scans() {
		ScanWoIdPath path = new ScanWoIdPath(baseUri);
		path.setHttpClientConfig(httpClientConfig);
		return path;
	}

	@SuppressWarnings("unused")
	public AclOwnService aclsOwn() {
		AclOwnService service = new AclOwnService(baseUri);
		setPrefs(service);
		return service;
	}

}
