package com.ripstech.apiconnector2.service.application.profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.application.profile.Ignore;
import com.ripstech.api.entity.send.application.profile.IgnoreSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class IgnoreService extends PatchDeletePostGetService<Ignore, IgnoreSend> {

	private final long applicationId;
	private final long profileId;

	public IgnoreService(String baseUri, long applicationId, long profileId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.profileId = profileId;
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
		return String.format("applications/%d/profiles/%d/ignores", applicationId, profileId);
	}

}
