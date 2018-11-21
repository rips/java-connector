package com.ripstech.apiconnector2.service.application.profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.application.profile.Sanitiser;
import com.ripstech.api.entity.send.application.profile.SanitiserSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class SanitiserService extends PatchDeletePostGetService<Sanitiser, SanitiserSend> {

	private final long applicationId;
	private final long profileId;

	public SanitiserService(String baseUri, long applicationId, long profileId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.profileId = profileId;
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
		return String.format("applications/%d/profiles/%d/sanitisers", applicationId, profileId);
	}

}
