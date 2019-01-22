package com.ripstech.api.connector.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.SimpleGetListService;
import com.ripstech.api.entity.receive.Setting;
import com.ripstech.api.entity.send.SettingSend;
import com.ripstech.api.connector.ApiResponse;
import com.ripstech.api.connector.service.queryparameter.Filter;

import java.util.List;

import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.*;

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
		return put(key, new SettingSend.Put(value));
	}

	public ApiResponse<Setting> get(String key) {
		return new ApiResponse<>(getTarget(GET)
				                         .appendPath(key),
		                         Setting.class);
	}
}
