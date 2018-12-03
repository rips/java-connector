package com.ripstech.apiconnector2.connector.path.application;

import com.ripstech.apiconnector2.connector.Path;
import com.ripstech.apiconnector2.service.application.profile.*;

public class ProfilePath extends Path {

	private final long applicationId;
	private final long profileId;

	public ProfilePath(String baseUri, long applicationId, long profileId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.profileId = profileId;
	}

	@SuppressWarnings("unused")
	public IgnoreService ignores() {
		IgnoreService service = new IgnoreService(baseUri, applicationId, profileId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SanitizerService sanitizers() {
		SanitizerService service = new SanitizerService(baseUri, applicationId, profileId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SinkService sinks() {
		SinkService service = new SinkService(baseUri, applicationId, profileId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SourceService sources() {
		SourceService service = new SourceService(baseUri, applicationId, profileId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ValidatorService validators() {
		ValidatorService service = new ValidatorService(baseUri, applicationId, profileId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SettingService settings() {
		SettingService service = new SettingService(baseUri, applicationId, profileId);
		setPrefs(service);
		return service;
	}

}
