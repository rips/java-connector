package com.ripstech.apiconnector2.connector.path.application;

import com.ripstech.apiconnector2.connector.Path;
import com.ripstech.apiconnector2.service.application.custom.*;

public class CustomPath extends Path {

	private final long applicationId;
	private final long customId;

	public CustomPath(String baseUri, long applicationId, long customId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.customId = customId;
	}

	@SuppressWarnings("unused")
	public IgnoreService ignores() {
		IgnoreService service = new IgnoreService(baseUri, applicationId, customId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SanitiserService sanitisers() {
		SanitiserService service = new SanitiserService(baseUri, applicationId, customId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SinkService sinks() {
		SinkService service = new SinkService(baseUri, applicationId, customId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SourceService sources() {
		SourceService service = new SourceService(baseUri, applicationId, customId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ValidatorService validators() {
		ValidatorService service = new ValidatorService(baseUri, applicationId, customId);
		setPrefs(service);
		return service;
	}

}
