package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.entity.receive.Setting;
import com.ripstech.apiconnector2.entity.send.SettingSend;
import com.ripstech.apiconnector2.service.queryparameter.Filter;
import com.ripstech.apiconnector2.service.template.SimpleGetListService;

import java.util.List;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.*;

public class SettingService extends SimpleGetListService<Setting> {

	public SettingService(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "settings";
	}

	@Override
	public TypeReference<List<Setting>> getGenericListType() {
		return new TypeReference<List<Setting>>() {};
	}

	public ApiResponse<Void> delete(Filter filter) {
		return new ApiResponse<>(getTarget(DELETE)
				                         .addQueryParams(filter),
		                         Void.class);
	}

	public ApiResponse<Void> delete(String key) {
		return new ApiResponse<>(getTarget(DELETE)
				                         .appendPath(key),
		                         Void.class);
	}

	public ApiResponse<Setting> put(String key, SettingSend setting) {
		return new ApiResponse<>(getTarget(PUT)
				                         .appendPath(key)
				                         .setJsonBody(setting),
		                         Setting.class);
	}

	public ApiResponse<Setting> put(String key, String value) {
		return put(key, SettingSend.createPut(value));
	}

	public ApiResponse<Setting> get(String key) {
		return new ApiResponse<>(getTarget(GET)
				                         .appendPath(key),
		                         Setting.class);
	}
}
