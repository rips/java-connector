package com.ripstech.apiconnector2.connector.path.application;

import com.ripstech.apiconnector2.connector.Path;
import com.ripstech.apiconnector2.connector.path.application.scan.IssueWoIdPath;
import com.ripstech.apiconnector2.service.application.scan.AllService;
import com.ripstech.apiconnector2.service.application.scan.PropertyTypeService;
import com.ripstech.apiconnector2.service.application.scan.StatService;

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
