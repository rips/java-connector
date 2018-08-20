package com.ripstech.apiconnector2.service.application.custom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.entity.receive.application.custom.Setting;
import com.ripstech.apiconnector2.entity.send.application.custom.SettingSend;
import com.ripstech.apiconnector2.service.template.SimpleGetService;

public class SettingService extends SimpleGetService<Setting> {

	private long applicationId;
	private long customId;

	public SettingService(String baseUri, long applicationId, long customId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.customId = customId;
	}

	@Override
	public TypeReference<Setting> getGenericType() {
		return new TypeReference<Setting>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/customs/%d/settings", applicationId, customId);
	}

	public ApiResponse<Setting> put(SettingSend setting) {
		return new ApiResponse<>(getTarget(HttpMethod.PUT).setJsonBody(setting), getGenericType());
	}
}
