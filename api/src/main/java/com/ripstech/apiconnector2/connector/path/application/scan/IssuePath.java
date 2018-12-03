package com.ripstech.apiconnector2.connector.path.application.scan;

import com.ripstech.apiconnector2.connector.Path;
import com.ripstech.apiconnector2.service.application.scan.issue.*;

public class IssuePath extends Path {

	private final long applicationId;
	private final long scanId;
	private final long issueId;

	public IssuePath(String baseUri, long applicationId, long scanId, long issueId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
		this.issueId = issueId;
	}

	@SuppressWarnings("unused")
	public CommentService comments() {
		CommentService service = new CommentService(baseUri, applicationId, scanId, issueId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ContextService contexts() {
		ContextService service = new ContextService(baseUri, applicationId, scanId, issueId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ReviewService reviews() {
		ReviewService service = new ReviewService(baseUri, applicationId, scanId, issueId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SummaryService summaries() {
		SummaryService service = new SummaryService(baseUri, applicationId, scanId, issueId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public PatchService patches() {
		PatchService service = new PatchService(baseUri, applicationId, scanId, issueId);
		setPrefs(service);
		return service;
	}

}
