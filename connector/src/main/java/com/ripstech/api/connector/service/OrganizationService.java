package com.ripstech.api.connector.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.ApiResponse;
import com.ripstech.api.connector.service.template.PatchDeletePostGetService;
import com.ripstech.api.entity.receive.Organization;
import com.ripstech.api.entity.send.OrganizationSend;
import com.ripstech.api.entity.send.organization.invite.UiSend;

import java.util.List;

import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.POST;

public class OrganizationService extends PatchDeletePostGetService<Organization, OrganizationSend> {

	public OrganizationService(String baseUri) {
		super(baseUri);
	}

	public ApiResponse<Void> inviteUi(com.ripstech.api.entity.send.organization.invite.UiSend invite) {
		return new ApiResponse<>(getTarget(POST, false)
				.appendPath("invite/ui")
				.setJsonBody(new OrganizationWrapper(invite))
				.setSendAuthHeader(false),
			Void.class);
	}

	@Override
	protected String getPath() {
		return "organizations";
	}

	@Override
	public TypeReference<Organization> getGenericType() {
		return new TypeReference<Organization>() {};
	}

	@Override
	public TypeReference<List<Organization>> getGenericListType() {
		return new TypeReference<List<Organization>>() {};
	}

	private static class OrganizationWrapper {
		public UiSend organization;

		public OrganizationWrapper(UiSend organization) {
			this.organization = organization;
		}
	}
}
