package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.Application;
import com.ripstech.api.entity.send.ApplicationSend;
import com.ripstech.apiconnector2.connector.path.application.ScanWoIdPath;
import com.ripstech.apiconnector2.service.application.AclOwnService;
import com.ripstech.apiconnector2.service.template.PatchDeletePostGetService;

import java.util.List;

public class ApplicationService extends PatchDeletePostGetService<Application, ApplicationSend> {

	public ApplicationService(String baseUri) {
		super(baseUri);
	}

	@Override
	protected String getPath() {
		return "applications";
	}

	@Override
	public TypeReference<Application> getGenericType() {
		return new TypeReference<Application>() {};
	}

	@Override
	public TypeReference<List<Application>> getGenericListType() {
		return new TypeReference<List<Application>>() {};
	}

	public ScanWoIdPath scans() {
		ScanWoIdPath path = new ScanWoIdPath(baseUri);
		path.setHttpClientConfig(httpClientConfig);
		return path;
	}

	@SuppressWarnings("unused")
	public AclOwnService aclsOwn() {
		AclOwnService service = new AclOwnService(baseUri);
		service.setHttpClientConfig(httpClientConfig);
		return service;
	}

}
