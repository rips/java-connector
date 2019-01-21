package com.ripstech.api.utils.application;

import com.ripstech.api.entity.receive.Application;
import com.ripstech.api.entity.helper.IdHolder;
import com.ripstech.api.connector.Api;
import com.ripstech.api.connector.exception.ApiException;
import com.ripstech.api.connector.service.queryparameter.Filter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationHandler {

	//TODO use global Language enum of connector
	private static Map<Long, String> langMapping;

	static {
		langMapping = new HashMap<>();
		langMapping.put(1L, "PHP");
		langMapping.put(2L, "Java");
	}

	private ApplicationHandler() {
	}

	public static UserApplications getApplications(Api api) throws ApiException {
		return getApplications(api, Filter.empty());
	}

	public static UserApplications getApplications(Api api, Filter filter) throws ApiException {
		return new UserApplications(
				api.applications()
				   .get(filter)
				   .orThrow(ApiException::new)
				   .stream()
				   .collect(Collectors.toMap(Application::getId, app -> {
					   String lang = app.getChargedQuota()
					                    .getLanguages()
					                    .stream()
					                    .findFirst()
					                    .map(IdHolder::getId)
					                    .map(langId -> langMapping.getOrDefault(langId, "unknown"))
					                    .orElse("unknown");
					   return new UserApplications.Entry(app.getName(), lang);
				   })));
	}

}
