package com.ripstech.apiconnector2.service.application.custom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.custom.Sanitiser;
import com.ripstech.apiconnector2.entity.send.application.custom.SanitiserSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class SanitiserService extends PatchDeletePostGetService<Sanitiser, SanitiserSend> {

	private final long applicationId;
	private final long customId;

	public SanitiserService(String baseUri, long applicationId, long customId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.customId = customId;
	}

	@Override
	public TypeReference<Sanitiser> getGenericType() {
		return new TypeReference<Sanitiser>() {};
	}

	@Override
	public TypeReference<List<Sanitiser>> getGenericListType() {
		return new TypeReference<List<Sanitiser>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/customs/%d/sanitisers", applicationId, customId);
	}

}
