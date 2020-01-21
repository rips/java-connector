package com.ripstech.api.connector.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.DeletePostGetService;
import com.ripstech.api.entity.receive.User;
import com.ripstech.api.entity.send.UserSend;
import com.ripstech.api.connector.ApiResponse;

import java.util.List;

import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.POST;

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
		return new ApiResponse<>(getTarget(POST, false)
				                         .appendPath("reset/ui")
				                         .setJsonBody(new ResetWrapper(reset))
				                         .setSendAuthHeader(false),
		                         Void.class);
	}

	public ApiResponse<Void> inviteUi(com.ripstech.api.entity.send.user.invite.UiSend invite) {
		return new ApiResponse<>(getTarget(POST, false)
				                         .appendPath("invite/ui")
				                         .setJsonBody(new UserWrapper(invite))
				                         .setSendAuthHeader(false),
		                         Void.class);
	}

	private static class ResetWrapper {

		public ResetWrapper(com.ripstech.api.entity.send.user.reset.UiSend reset) {
			this.reset = reset;
		}

		public com.ripstech.api.entity.send.user.reset.UiSend reset;
	}

	private static class UserWrapper {

		public UserWrapper(com.ripstech.api.entity.send.user.invite.UiSend user) {
			this.user = user;
		}

		public com.ripstech.api.entity.send.user.invite.UiSend user;
	}

}
