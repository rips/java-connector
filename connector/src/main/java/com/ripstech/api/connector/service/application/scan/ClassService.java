package com.ripstech.api.connector.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.ApiResponse;
import com.ripstech.api.connector.service.template.GetService;
import com.ripstech.api.entity.receive.application.scan.Class;
import com.ripstech.api.entity.send.application.scan.ClassesSend;

import java.util.List;

import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.POST;

public class ClassService extends GetService<Class> {

	private final long applicationId;
	private final long scanId;

	public ClassService(String baseUri, long applicationId, long scanId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
	}

	@Override
	public TypeReference<Class> getGenericType() {
		return new TypeReference<Class>() {};
	}

	@Override
	public TypeReference<List<Class>> getGenericListType() {
		return new TypeReference<List<Class>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/classes", applicationId, scanId);
	}

	public ApiResponse<List<Class>> postBatches(ClassesSend applicationScanClass) {
		return new ApiResponse<>(getTarget(POST)
				                         .appendPath("batches")
				                         .setJsonBody(applicationScanClass),
		                         getGenericListType());
	}

}
