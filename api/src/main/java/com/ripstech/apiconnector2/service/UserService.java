package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.User;
import com.ripstech.api.entity.send.UserSend;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.service.template.DeletePostGetService;

import java.util.List;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.POST;

public class UserService extends DeletePostGetService<User, UserSend> {

	public UserService(String baseUri) {
		super(baseUri);
	}

	@Override
	public TypeReference<User> getGenericType() {
		return new TypeReference<User>() {};
	}

	@Override
	public TypeReference<List<User>> getGenericListType() {
		return new TypeReference<List<User>>() {};
	}

	@Override
	protected String getPath() {
		return "users";
	}

	public ApiResponse<Void> resetUi(com.ripstech.api.entity.send.user.reset.UiSend reset) {
		return new ApiResponse<>(getTarget(POST)
				                         .appendPath("reset/ui")
				                         .setJsonBody(reset)
				                         .setSendAuthHeader(false),
		                         Void.class);
	}

	public ApiResponse<Void> inviteUi(com.ripstech.api.entity.send.user.invite.UiSend reset) {
		return new ApiResponse<>(getTarget(POST)
				                         .appendPath("invite/ui")
				                         .setJsonBody(reset)
				                         .setSendAuthHeader(false),
		                         Void.class);
	}

}
