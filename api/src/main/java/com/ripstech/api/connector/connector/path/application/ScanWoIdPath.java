package com.ripstech.api.connector.connector.path.application;

import com.ripstech.api.connector.connector.path.application.scan.IssueWoIdPath;
import com.ripstech.api.connector.connector.Path;
import com.ripstech.api.connector.service.application.scan.AllService;
import com.ripstech.api.connector.service.application.scan.PropertyTypeService;
import com.ripstech.api.connector.service.application.scan.StatService;

public class ScanWoIdPath extends Path {

	public ScanWoIdPath(String baseUri) {
		super(baseUri);
	}

	public IssueWoIdPath issues() {
		IssueWoIdPath path = new IssueWoIdPath(baseUri);
		path.setHttpClientConfig(httpClientConfig);
		return path;
	}

	@SuppressWarnings("unused")
	public StatService stats() {
		StatService service = new StatService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public AllService all() {
		AllService service = new AllService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public PropertyTypeService propertyTypes() {
		PropertyTypeService service = new PropertyTypeService(baseUri);
		setPrefs(service);
		return service;
	}

}
