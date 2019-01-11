package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.Client;
import com.ripstech.api.entity.send.ClientSend;
import com.ripstech.apiconnector2.service.template.PutDeletePostGetService;

import java.util.List;

public class OAuthv2ClientService extends PutDeletePostGetService<Client, ClientSend> {

	public OAuthv2ClientService(String baseUri) {
		super(baseUri);
	}

	@Override
	public TypeReference<List<Client>> getGenericListType() {
		return new TypeReference<List<Client>>() {};
	}

	@Override
	public TypeReference<Client> getGenericType() {
		return new TypeReference<Client>() {};
	}

	@Override
	protected String getPath() {
		return "oauth/v2/clients";
	}

}
