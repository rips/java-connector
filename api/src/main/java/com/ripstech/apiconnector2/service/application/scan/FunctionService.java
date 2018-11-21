package com.ripstech.apiconnector2.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.application.scan.Function;
import com.ripstech.api.entity.send.application.scan.FunctionsSend;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.service.template.GetService;

import java.util.List;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.POST;

public class FunctionService extends GetService<Function> {

	private final long applicationId;
	private final long scanId;

	public FunctionService(String baseUri, long applicationId, long scanId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
	}

	@Override
	public TypeReference<Function> getGenericType() {
		return new TypeReference<Function>() {};
	}

	@Override
	public TypeReference<List<Function>> getGenericListType() {
		return new TypeReference<List<Function>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/functions", applicationId, scanId);
	}

	public ApiResponse<List<Function>> postBatches(FunctionsSend applicationScanFunction) {
		return new ApiResponse<>(getTarget(POST)
				                         .appendPath("batches")
				                         .setJsonBody(applicationScanFunction),
		                         getGenericListType());
	}

}
