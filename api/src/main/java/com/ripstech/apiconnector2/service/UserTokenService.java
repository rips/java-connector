package com.ripstech.apiconnector2.service;

import com.ripstech.api.entity.receive.User;
import com.ripstech.api.entity.send.user.ActivateSend;
import com.ripstech.api.entity.send.user.ResetSend;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.annotation.AuthRequired;
import com.ripstech.apiconnector2.service.template.GenericService;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.POST;

@AuthRequired(false)
public class UserTokenService extends GenericService {

	private final int userId;

	public UserTokenService(String baseUri, int userId) {
		super(baseUri);
		this.userId = userId;
	}

	@Override
	protected String getPath() {
		return String.format("users/%d", userId);
	}

	public ApiResponse<User> activate(String token, ActivateSend userActivate) {
		return new ApiResponse<>(getTarget(POST)
				                         .appendPath(String.format("activate/%s", token))
				                         .setJsonBody(userActivate),
		                         User.class);
	}

	public ApiResponse<User> confirm(String token) {
		return new ApiResponse<>(getTarget(POST)
				                         .appendPath(String.format("confirm/%s", token)),
		                         User.class);
	}

	public ApiResponse<User> reset(String token, ResetSend userReset) {
		return new ApiResponse<>(getTarget(POST)
				                         .appendPath(String.format("reset/%s", token))
				                         .setJsonBody(userReset),
		                         User.class);
	}

}
