package com.ripstech.apiconnector2.service.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.entity.receive.application.Scan;
import com.ripstech.apiconnector2.entity.receive.application.scan.Stats;
import com.ripstech.apiconnector2.entity.send.application.ScanPatch;
import com.ripstech.apiconnector2.entity.send.application.ScanPost;
import com.ripstech.apiconnector2.service.queryparameter.Filter;
import com.ripstech.apiconnector2.service.template.DeletePostGetService;

import java.util.List;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;
import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.PATCH;

public class ScanService extends DeletePostGetService<Scan, ScanPost> {

	private final long applicationId;

	public ScanService(String baseUri, long applicationId) {
		super(baseUri);
		this.applicationId = applicationId;
		withRootName = false;
	}

	@Override
	public TypeReference<Scan> getGenericType() {
		return new TypeReference<Scan>() {};
	}

	@Override
	public TypeReference<List<Scan>> getGenericListType() {
		return new TypeReference<List<Scan>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans", applicationId);
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

	public ApiResponse<Scan> patch(long scanId, ScanPatch scan) {
		return new ApiResponse<>(getTarget(PATCH)
				                         .appendPath(scanId)
				                         .setJsonBody(scan),
		                         Scan.class);
	}

}
