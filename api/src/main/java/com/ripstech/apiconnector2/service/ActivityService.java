package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.Activity;
import com.ripstech.apiconnector2.service.template.GetService;

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
