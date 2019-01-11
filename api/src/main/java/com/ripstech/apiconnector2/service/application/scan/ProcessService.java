package com.ripstech.apiconnector2.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.application.scan.Process;
import com.ripstech.api.entity.send.application.scan.ProcessSend;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.service.template.PostGetService;

import java.time.OffsetDateTime;
import java.util.List;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.PATCH;

public class ProcessService extends PostGetService<Process, ProcessSend.Post> {

	private final long applicationId;
	private final long scanId;

	public ProcessService(String baseUri, long applicationId, long scanId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
	}

	@Override
	public TypeReference<Process> getGenericType() {
		return new TypeReference<Process>() {};
	}

	@Override
	public TypeReference<List<Process>> getGenericListType() {
		return new TypeReference<List<Process>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/processes", applicationId, scanId);
	}

	public ApiResponse<Process> patch(long processId, ProcessSend.Patch finish) {
		return new ApiResponse<>(getTarget(PATCH)
				                         .appendPath(processId)
				                         .setJsonBody(finish),
		                         getGenericType());
	}

	public ApiResponse<Process> patch(long processId, OffsetDateTime finish) {
		return patch(processId, new ProcessSend.Patch().setFinishedAt(finish));
	}

}
