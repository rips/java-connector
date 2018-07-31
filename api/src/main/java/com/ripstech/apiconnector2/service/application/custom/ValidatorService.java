package com.ripstech.apiconnector2.service.application.custom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.custom.Validator;
import com.ripstech.apiconnector2.entity.send.application.custom.ValidatorSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class ValidatorService extends PatchDeletePostGetService<Validator, ValidatorSend> {

	private final int applicationId;
	private final int customId;

	public ValidatorService(String baseUri, int applicationId, int customId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.customId = customId;
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
		return String.format("applications/%d/customs/%d/validators", applicationId, customId);
	}

}
