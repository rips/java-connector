package com.ripstech.api.connector.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.GetService;
import com.ripstech.api.entity.receive.application.scan.property.Type;

import java.util.List;

public class PropertyTypeService extends GetService<Type> {

	public PropertyTypeService(String baseUri) {
		super(baseUri);
	}

	@Override
	public TypeReference<Type> getGenericType() {
		return new TypeReference<Type>() {};
	}

	@Override
	public TypeReference<List<Type>> getGenericListType() {
		return new TypeReference<List<Type>>() {};
	}

	@Override
	protected String getPath() {
		return "applications/scans/properties/types";
	}

}
