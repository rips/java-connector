package com.ripstech.apiconnector2.service.application.profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.application.profile.Sink;
import com.ripstech.api.entity.send.application.profile.SinkSend;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class SinkService extends PatchDeletePostGetService<Sink, SinkSend> {

	private final long applicationId;
	private final long profileId;

	public SinkService(String baseUri, long applicationId, long profileId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.profileId = profileId;
	}

	@Override
	public TypeReference<Sink> getGenericType() {
		return new TypeReference<Sink>() {};
	}

	@Override
	public TypeReference<List<Sink>> getGenericListType() {
		return new TypeReference<List<Sink>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/profiles/%d/sinks", applicationId, profileId);
	}

}
