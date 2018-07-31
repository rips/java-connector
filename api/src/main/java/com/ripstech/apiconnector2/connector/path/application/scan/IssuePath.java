package com.ripstech.apiconnector2.connector.path.application.scan;

import com.ripstech.apiconnector2.connector.Path;
import com.ripstech.apiconnector2.service.application.scan.issue.CommentService;
import com.ripstech.apiconnector2.service.application.scan.issue.MarkupService;
import com.ripstech.apiconnector2.service.application.scan.issue.ReviewService;
import com.ripstech.apiconnector2.service.application.scan.issue.SummaryService;

public class IssuePath extends Path {

	private final int applicationId;
	private final int scanId;
	private final int issueId;

	public IssuePath(String baseUri, int applicationId, int scanId, int issueId) {
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
	public MarkupService markups() {
		MarkupService service = new MarkupService(baseUri, applicationId, scanId, issueId);
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

}
