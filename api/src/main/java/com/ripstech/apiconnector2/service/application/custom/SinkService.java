package com.ripstech.apiconnector2.service.application.custom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.custom.Sink;
import com.ripstech.apiconnector2.entity.send.application.custom.SinkSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class SinkService extends PatchDeletePostGetService<Sink, SinkSend> {

	private final int applicationId;
	private final int customId;

	public SinkService(String baseUri, int applicationId, int customId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.customId = customId;
	}

	@Override
	public TypeReference<Sink> getGenericType() {
		return new TypeReference<Sink>() {};
	}

	@Override
	public TypeReference<List<Sink>> getGenericListType() {
		return new TypeReference<List<Sink>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/customs/%d/sinks", applicationId, customId);
	}

}
