package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.Callback;
import com.ripstech.api.entity.send.CallbackSend;
import com.ripstech.apiconnector2.service.template.DeletePostGetService;

import java.util.List;

public class CallbackService extends DeletePostGetService<Callback, CallbackSend> {

	public CallbackService(String baseUri) {
		super(baseUri);
	}

	@Override
	public TypeReference<Callback> getGenericType() {
		return new TypeReference<Callback>() {};
	}

	@Override
	public TypeReference<List<Callback>> getGenericListType() {
		return new TypeReference<List<Callback>>() {};
	}

	@Override
	protected String getPath() {
		return "/callbacks";
	}

}
