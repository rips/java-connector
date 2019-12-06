package com.ripstech.api.connector.connector.path.application;

import com.ripstech.api.connector.connector.Path;
import com.ripstech.api.connector.service.application.profile.*;

public class ProfilePath extends Path {

	private final long applicationId;
	private final long profileId;

	public ProfilePath(String baseUri, long applicationId, long profileId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.profileId = profileId;
	}

	@SuppressWarnings("unused")
	public IgnoredCodesService ignoredCodes() {
		IgnoredCodesService service = new IgnoredCodesService(baseUri, applicationId, profileId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public IgnoredLocationsService ignoredLocations() {
		IgnoredLocationsService service = new IgnoredLocationsService(baseUri, applicationId, profileId);
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
