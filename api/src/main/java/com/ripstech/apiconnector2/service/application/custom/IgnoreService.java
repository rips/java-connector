package com.ripstech.apiconnector2.service.application.custom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.custom.Ignore;
import com.ripstech.apiconnector2.entity.send.application.custom.IgnoreSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class IgnoreService extends PatchDeletePostGetService<Ignore, IgnoreSend> {

	private final long applicationId;
	private final long customId;

	public IgnoreService(String baseUri, long applicationId, long customId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.customId = customId;
	}

	@Override
	public TypeReference<Ignore> getGenericType() {
		return new TypeReference<Ignore>() {};
	}

	@Override
	public TypeReference<List<Ignore>> getGenericListType() {
		return new TypeReference<List<Ignore>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/customs/%d/ignores", applicationId, customId);
	}

}
