package com.ripstech.apiconnector2.service.application.scan.issue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.annotation.AuthRequired;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.OriginType;
import com.ripstech.apiconnector2.service.template.GetService;

import java.util.List;

@AuthRequired(false)
public class OriginTypeService extends GetService<OriginType> {

	public OriginTypeService(String baseUri) {
		super(baseUri);
	}

	@Override
	public TypeReference<OriginType> getGenericType() {
		return new TypeReference<OriginType>() {};
	}

	@Override
	public TypeReference<List<OriginType>> getGenericListType() {
		return new TypeReference<List<OriginType>>() {};
	}

	@Override
	protected String getPath() {
		return "applications/scans/issues/origins/types";
	}
}
