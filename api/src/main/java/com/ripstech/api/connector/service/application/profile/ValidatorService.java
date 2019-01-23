package com.ripstech.api.connector.service.application.profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.PatchDeletePostGetService;
import com.ripstech.api.entity.receive.application.profile.Validator;
import com.ripstech.api.entity.send.application.profile.ValidatorSend;

import java.util.List;

public class ValidatorService extends PatchDeletePostGetService<Validator, ValidatorSend> {

	private final long applicationId;
	private final long profileId;

	public ValidatorService(String baseUri, long applicationId, long profileId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.profileId = profileId;
	}

	@Override
	public TypeReference<Validator> getGenericType() {
		return new TypeReference<Validator>() {};
	}

	@Override
	public TypeReference<List<Validator>> getGenericListType() {
		return new TypeReference<List<Validator>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/profiles/%d/validators", applicationId, profileId);
	}

}
