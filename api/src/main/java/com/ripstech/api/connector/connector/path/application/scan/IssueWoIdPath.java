package com.ripstech.api.connector.connector.path.application.scan;

import com.ripstech.api.connector.connector.Path;
import com.ripstech.api.connector.service.application.scan.issue.OriginTypeService;
import com.ripstech.api.connector.service.application.scan.issue.PatchTypeService;
import com.ripstech.api.connector.service.application.scan.issue.ReviewTypeService;
import com.ripstech.api.connector.service.application.scan.issue.TypeService;

public class IssueWoIdPath extends Path {

	public IssueWoIdPath(String baseUri) {
		super(baseUri);
	}

	@SuppressWarnings("unused")
	public TypeService types() {
		TypeService service = new TypeService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public OriginTypeService originTypes() {
		OriginTypeService service = new OriginTypeService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ReviewTypeService reviewTypes() {
		ReviewTypeService service = new ReviewTypeService(baseUri);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public PatchTypeService patchTypes() {
		PatchTypeService service = new PatchTypeService(baseUri);
		setPrefs(service);
		return service;
	}

}
