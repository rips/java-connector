package com.ripstech.apiconnector2.connector.path;

import com.ripstech.apiconnector2.connector.Path;
import com.ripstech.apiconnector2.connector.path.application.CustomPath;
import com.ripstech.apiconnector2.connector.path.application.ScanPath;
import com.ripstech.apiconnector2.service.application.AclService;
import com.ripstech.apiconnector2.service.application.CustomService;
import com.ripstech.apiconnector2.service.application.ScanService;
import com.ripstech.apiconnector2.service.application.UploadService;

public class ApplicationPath extends Path {

	private final int applicationId;

	public ApplicationPath(String baseUri, int applicationId) {
		super(baseUri);
		this.applicationId = applicationId;
	}

	public ScanPath scan(int scanId) {
		ScanPath path = new ScanPath(baseUri, applicationId, scanId);
		path.setHttpClientConfig(httpClientConfig);
		return path;
	}

	public CustomPath custom(int customId) {
		CustomPath path = new CustomPath(baseUri, applicationId, customId);
		path.setHttpClientConfig(httpClientConfig);
		return path;
	}

	@SuppressWarnings("unused")
	public AclService acls() {
		AclService service = new AclService(baseUri, applicationId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public UploadService uploads() {
		UploadService service = new UploadService(baseUri, applicationId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ScanService scans() {
		ScanService service = new ScanService(baseUri, applicationId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public CustomService customs() {
		CustomService service = new CustomService(baseUri, applicationId);
		setPrefs(service);
		return service;
	}

}
