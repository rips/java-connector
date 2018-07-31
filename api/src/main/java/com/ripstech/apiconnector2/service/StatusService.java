package com.ripstech.apiconnector2.service;

import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.entity.receive.Status;
import com.ripstech.apiconnector2.service.template.GenericService;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;

public class StatusService extends GenericService {

	public StatusService(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "status";
	}

	public ApiResponse<Status> get() {
		return new ApiResponse<>(getTarget(GET), Status.class);
	}

}
