package com.ripstech.apiconnector2.service.application.profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.application.profile.Source;
import com.ripstech.api.entity.send.application.profile.SourceSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class SourceService extends PatchDeletePostGetService<Source, SourceSend> {

	private final long applicationId;
	private final long profileId;

	public SourceService(String baseUri, long applicationId, long profileId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.profileId = profileId;
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
		return String.format("applications/%d/profiles/%d/sources", applicationId, profileId);
	}

}
