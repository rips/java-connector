package com.ripstech.apiconnector2.service.application.scan.issue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.annotation.AuthRequired;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.ReviewType;
import com.ripstech.apiconnector2.service.template.GetService;

import java.util.List;

@AuthRequired(false)
public class ReviewTypeService extends GetService<ReviewType> {

	public ReviewTypeService(String baseUri) {
		super(baseUri);
	}

	@Override
	public TypeReference<ReviewType> getGenericType() {
		return new TypeReference<ReviewType>() {};
	}

	@Override
	public TypeReference<List<ReviewType>> getGenericListType() {
		return new TypeReference<List<ReviewType>>() {};
	}

	@Override
	protected String getPath() {
		return "applications/scans/issues/reviews/types";
	}
}
