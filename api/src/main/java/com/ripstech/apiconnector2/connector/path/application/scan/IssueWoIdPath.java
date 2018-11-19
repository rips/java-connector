package com.ripstech.apiconnector2.connector.path.application.scan;

import com.ripstech.apiconnector2.connector.Path;
import com.ripstech.apiconnector2.service.application.scan.issue.*;

public class IssueWoIdPath extends Path {

	public IssueWoIdPath(String baseUri) {
		super(baseUri);
	}

	@SuppressWarnings("unused")
	public CommentAllService commentsAll() {
		CommentAllService service = new CommentAllService(baseUri);
		setPrefs(service);
		return service;
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
