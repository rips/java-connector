package com.ripstech.api.utils.profiles;

import com.ripstech.api.connector.Api;
import com.ripstech.api.connector.exception.ApiException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ProfileHandler {

	private ProfileHandler() {
	}

	public static AnalysisProfiles getAnalysisProfiles(Api api, long appId) throws ApiException {
		AtomicLong defaultApplicationId = new AtomicLong(-1);
		AtomicLong defaultGlobalId = new AtomicLong(-1);
		final Map<Long, AnalysisProfiles.Entry> profiles = new HashMap<>();
		api.application(appId)
		   .profiles()
		   .getGlobals()
		   .orThrow(ApiException::new)
		   .forEach(p -> {
			   profiles.put(p.getId(), new AnalysisProfiles.Entry(p.getName(),
			                                                      p.getApplication() == null,
			                                                      p.getSetting().getAnalysisDepth()));
			   if (p.getDefault_()) {
				   if (p.getApplication() == null) {
					   defaultGlobalId.set(p.getId());
				   } else {
					   defaultApplicationId.set(p.getId());
				   }
			   }
		   });
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
