package com.ripstech.apiconnector2.service.application.custom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.custom.Source;
import com.ripstech.apiconnector2.entity.send.application.custom.SourceSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class SourceService extends PatchDeletePostGetService<Source, SourceSend> {

	private final int applicationId;
	private final int customId;

	public SourceService(String baseUri, int applicationId, int customId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.customId = customId;
	}

	@Override
	public TypeReference<Source> getGenericType() {
		return new TypeReference<Source>() {};
	}

	@Override
	public TypeReference<List<Source>> getGenericListType() {
		return new TypeReference<List<Source>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/customs/%d/sources", applicationId, customId);
	}

}
