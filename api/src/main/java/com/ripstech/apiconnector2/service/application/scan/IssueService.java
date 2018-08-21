package com.ripstech.apiconnector2.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.entity.receive.application.scan.Issue;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.Stats;
import com.ripstech.apiconnector2.entity.send.application.scan.IssueSend;
import com.ripstech.apiconnector2.service.queryparameter.Filter;
import com.ripstech.apiconnector2.service.template.PostGetService;

import java.util.List;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;

public class IssueService extends PostGetService<Issue, IssueSend> {

	private final long applicationId;
	private final long scanId;

	public IssueService(String baseUri, long applicationId, long scanId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
		withRootName = false;
	}

	@Override
	public TypeReference<Issue> getGenericType() {
		return new TypeReference<Issue>() {};
	}

	@Override
	public TypeReference<List<Issue>> getGenericListType() {
		return new TypeReference<List<Issue>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/issues", applicationId, scanId);
	}

	public ApiResponse<Stats> getStats(Filter filter) {
		return new ApiResponse<>(getTarget(GET)
				                         .appendPath("stats")
				                         .addQueryParams(filter),
		                         Stats.class);
	}

	public ApiResponse<Stats> getStats() {
		return getStats(Filter.empty());
	}

	public ApiResponse<List<Issue>> get(Filter filter, boolean minimal) {
		return new ApiResponse<>(getTarget(GET)
				                         .addQueryParams(filter)
				                         .addQueryParam("minimal", minimal),
		                         getGenericListType());
	}

	public ApiResponse<List<Issue>> get(boolean minimal) {
		return get(Filter.empty(), minimal);
	}

	public ApiResponse<Issue> get(int id, boolean minimal) {
		return new ApiResponse<>(getTarget(GET)
				                         .appendPath(id)
				                         .addQueryParam("minimal", minimal),
		                         getGenericType());
	}
}
