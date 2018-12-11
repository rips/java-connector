package com.ripstech.apiconnector2.service.application.profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.application.profile.IgnoredLocation;
import com.ripstech.api.entity.send.application.profile.IgnoredLocationSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class IgnoredLocationsService extends PatchDeletePostGetService<IgnoredLocation, IgnoredLocationSend> {

	private final long applicationId;
	private final long profileId;

	public IgnoredLocationsService(String baseUri, long applicationId, long profileId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.profileId = profileId;
	}

	@Override
	public TypeReference<IgnoredLocation> getGenericType() {
		return new TypeReference<IgnoredLocation>() {};
	}

	@Override
	public TypeReference<List<IgnoredLocation>> getGenericListType() {
		return new TypeReference<List<IgnoredLocation>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/profiles/%d/ignoredlocations", applicationId, profileId);
	}

}
