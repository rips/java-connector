package com.ripstech.apiconnector2.service.application.profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.application.profile.Sanitizer;
import com.ripstech.api.entity.send.application.profile.SanitizerSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class SanitizerService extends PatchDeletePostGetService<Sanitizer, SanitizerSend> {

	private final long applicationId;
	private final long profileId;

	public SanitizerService(String baseUri, long applicationId, long profileId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.profileId = profileId;
	}

	@Override
	public TypeReference<Sanitizer> getGenericType() {
		return new TypeReference<Sanitizer>() {};
	}

	@Override
	public TypeReference<List<Sanitizer>> getGenericListType() {
		return new TypeReference<List<Sanitizer>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/profiles/%d/sanitizers", applicationId, profileId);
	}

}
