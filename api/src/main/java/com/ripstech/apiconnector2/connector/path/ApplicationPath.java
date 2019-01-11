package com.ripstech.apiconnector2.connector.path;

import com.ripstech.apiconnector2.connector.Path;
import com.ripstech.apiconnector2.connector.path.application.ProfilePath;
import com.ripstech.apiconnector2.connector.path.application.ScanPath;
import com.ripstech.apiconnector2.service.application.AclService;
import com.ripstech.apiconnector2.service.application.ProfileService;
import com.ripstech.apiconnector2.service.application.ScanService;
import com.ripstech.apiconnector2.service.application.UploadService;

public class ApplicationPath extends Path {

	private final long applicationId;

	public ApplicationPath(String baseUri, long applicationId) {
		super(baseUri);
		this.applicationId = applicationId;
	}

	public ScanPath scan(long scanId) {
		ScanPath path = new ScanPath(baseUri, applicationId, scanId);
		path.setHttpClientConfig(httpClientConfig);
		return path;
	}

	@SuppressWarnings("unused")
	public ProfilePath profiles(long profileId) {
		ProfilePath path = new ProfilePath(baseUri, applicationId, profileId);
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
	public ProfileService profiles() {
		ProfileService service = new ProfileService(baseUri, applicationId);
		setPrefs(service);
		return service;
	}

}
