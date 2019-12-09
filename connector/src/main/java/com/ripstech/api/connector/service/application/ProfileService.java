package com.ripstech.api.connector.service.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.ApiResponse;
import com.ripstech.api.connector.service.queryparameter.Filter;
import com.ripstech.api.connector.service.template.PatchDeletePostGetService;
import com.ripstech.api.entity.receive.application.Profile;
import com.ripstech.api.entity.send.application.ProfileSend;

import java.util.List;

import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.GET;

public class ProfileService extends PatchDeletePostGetService<Profile, ProfileSend> {

	private final long applicationId;

	public ProfileService(String baseUri, long applicationId) {
		super(baseUri);
		this.applicationId = applicationId;
	}

	@Override
	public TypeReference<Profile> getGenericType() {
		return new TypeReference<Profile>() {};
	}

	@Override
	public TypeReference<List<Profile>> getGenericListType() {
		return new TypeReference<List<Profile>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/profiles", applicationId);
	}

	public ApiResponse<List<Profile>> getGlobals(Filter filter) {
		return new ApiResponse<>(getTarget(GET)
				                         .setQueryParams(filter)
				                         .appendPath("globals"),
		                         getGenericListType());
	}

	public ApiResponse<List<Profile>> getGlobals() {
		return getGlobals(Filter.empty());
	}

}
