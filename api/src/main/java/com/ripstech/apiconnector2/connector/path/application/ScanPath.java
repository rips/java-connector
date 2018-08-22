package com.ripstech.apiconnector2.connector.path.application;

import com.ripstech.apiconnector2.connector.Path;
import com.ripstech.apiconnector2.connector.path.application.scan.IssuePath;
import com.ripstech.apiconnector2.service.application.scan.*;

public class ScanPath extends Path {

	private final long applicationId;
	private final long scanId;

	public ScanPath(String baseUri, long applicationId, long scanId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
	}

	public IssuePath issue(long issueId) {
		IssuePath path = new IssuePath(baseUri, applicationId, scanId, issueId);
		path.setHttpClientConfig(httpClientConfig);
		return path;
	}

	@SuppressWarnings("unused")
	public ComparisonService comparisons() {
		ComparisonService service = new ComparisonService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ClassService classes() {
		ClassService service = new ClassService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public FunctionService functions() {
		FunctionService service = new FunctionService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ConcatService concats() {
		ConcatService service = new ConcatService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public FileService files() {
		FileService service = new FileService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ProcessService processes() {
		ProcessService service = new ProcessService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SinkService sinks() {
		SinkService service = new SinkService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public SourceService sources() {
		SourceService service = new SourceService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public IssueService issues() {
		IssueService service = new IssueService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

	@SuppressWarnings("unused")
	public ExportService exports() {
		ExportService service = new ExportService(baseUri, applicationId, scanId);
		setPrefs(service);
		return service;
	}

}
