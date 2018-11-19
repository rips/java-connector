package com.ripstech.apiconnector2.service.application.profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.application.profile.Setting;
import com.ripstech.api.entity.send.application.profile.SettingSend;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.service.template.SimpleGetService;

public class SettingService extends SimpleGetService<Setting> {

	private long applicationId;
	private long profileId;

	public SettingService(String baseUri, long applicationId, long profileId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.profileId = profileId;
	}

	@Override
	public TypeReference<Setting> getGenericType() {
		return new TypeReference<Setting>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/profiles/%d/settings", applicationId, profileId);
	}

	public ApiResponse<Setting> put(SettingSend setting) {
		return new ApiResponse<>(getTarget(HttpMethod.PUT).setJsonBody(setting), getGenericType());
	}
}
