package com.ripstech.apiconnector2.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.entity.receive.application.scan.Difference;
import com.ripstech.apiconnector2.entity.receive.application.scan.DifferenceDetails;
import com.ripstech.apiconnector2.service.template.GenericService;

import java.util.Map;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;

public class ComparisonService extends GenericService {

	private final int applicationId;
	private final int scanId;

	public ComparisonService(String baseUri, int applicationId, int scanId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/comparison", applicationId, scanId);
	}

	public ApiResponse<Map<String, Difference>> get() {
		return new ApiResponse<>(getTarget(GET), new TypeReference<Map<String, Difference>>() {});
	}

	public ApiResponse<Map<String, DifferenceDetails>> getDetails() {
		return new ApiResponse<>(getTarget(GET).appendPath("details"),
		                         new TypeReference<Map<String, DifferenceDetails>>() {});
	}

}
