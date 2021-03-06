package com.ripstech.api.connector.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.GetService;
import com.ripstech.api.entity.receive.Activity;

import java.util.List;

public class ActivityService extends GetService<Activity> {

	public ActivityService(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "activities";
	}

	@Override
	public TypeReference<Activity> getGenericType() {
		return new TypeReference<Activity>() {};
	}

	@Override
	public TypeReference<List<Activity>> getGenericListType() {
		return new TypeReference<List<Activity>>() {};
	}
}
