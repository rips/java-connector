package com.ripstech.api.utils.profiles;

import com.ripstech.api.connector.Api;
import com.ripstech.api.connector.exception.ApiException;
import com.ripstech.api.entity.receive.application.Profile;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ProfileHandler {

	private ProfileHandler() {
	}

	public static AnalysisProfiles getAnalysisProfiles(Api api, long appId) throws ApiException {
		AtomicLong defaultApplicationId = new AtomicLong(-1);
		AtomicLong defaultGlobalId = new AtomicLong(-1);
		final Set<Profile> profiles = api.application(appId)
		                                 .profiles()
		                                 .getGlobals()
		                                 .orThrow(ApiException::new)
		                                 .stream()
		                                 .peek(p -> {
			                                 if (p.getDefault_()) {
				                                 if (p.getApplication() == null) {
					                                 defaultGlobalId.set(p.getId());
				                                 } else {
					                                 defaultApplicationId.set(p.getId());
				                                 }
			                                 }
		                                 })
		                                 .collect(Collectors.toSet());
		Long defaultId = null;
		if (defaultGlobalId.get() != -1) {
			defaultId = defaultGlobalId.get();
		}
		if (defaultApplicationId.get() != -1) {
			defaultId = defaultApplicationId.get();
		}
		return new AnalysisProfiles(profiles, defaultId);
	}

}
