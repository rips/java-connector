package com.ripstech.api.connector.service.application.profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.PatchDeletePostGetService;
import com.ripstech.api.entity.receive.application.profile.IgnoredCode;
import com.ripstech.api.entity.send.application.profile.IgnoredCodeSend;

import java.util.List;

public class IgnoredCodesService extends PatchDeletePostGetService<IgnoredCode, IgnoredCodeSend> {

	private final long applicationId;
	private final long profileId;

	public IgnoredCodesService(String baseUri, long applicationId, long profileId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.profileId = profileId;
	}

	@Override
	public TypeReference<IgnoredCode> getGenericType() {
		return new TypeReference<IgnoredCode>() {};
	}

	@Override
	public TypeReference<List<IgnoredCode>> getGenericListType() {
		return new TypeReference<List<IgnoredCode>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/profiles/%d/ignoredcodes", applicationId, profileId);
	}

}
