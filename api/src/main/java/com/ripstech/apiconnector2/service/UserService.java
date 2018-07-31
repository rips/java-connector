package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.entity.receive.User;
import com.ripstech.apiconnector2.entity.send.UserSend;
import com.ripstech.apiconnector2.entity.send.user.ui.InviteSend;
import com.ripstech.apiconnector2.entity.send.user.ui.ResetSend;
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

	public ApiResponse<Void> resetUi(ResetSend reset) {
		return new ApiResponse<>(getTarget(POST)
				                         .appendPath("reset/ui")
				                         .setJsonBody(reset)
				                         .setSendAuthHeader(false),
		                         Void.class);
	}

	public ApiResponse<Void> inviteUi(InviteSend reset) {
		return new ApiResponse<>(getTarget(POST)
				                         .appendPath("invite/ui")
				                         .setJsonBody(reset)
				                         .setSendAuthHeader(false),
		                         Void.class);
	}

}
