package com.ripstech.api.connector.connector.path;

import com.ripstech.api.connector.connector.Path;
import com.ripstech.api.connector.connector.path.application.ProfileWoIdPath;
import com.ripstech.api.connector.connector.path.application.ScanWoIdPath;
import com.ripstech.api.connector.service.application.AclOwnService;

public class ApplicationWoIdPath extends Path {

	public ApplicationWoIdPath(String baseUri) {
		super(baseUri);
	}

	public ScanWoIdPath scans() {
		ScanWoIdPath path = new ScanWoIdPath(baseUri);
		path.setHttpClientConfig(httpClientConfig);
		return path;
	}

	public ProfileWoIdPath profiles() {
		ProfileWoIdPath path = new ProfileWoIdPath(baseUri);
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
