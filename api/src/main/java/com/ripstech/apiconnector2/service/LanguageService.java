package com.ripstech.apiconnector2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.Language;
import com.ripstech.apiconnector2.service.template.GetService;

import java.util.List;

public class LanguageService extends GetService<Language> {

	public LanguageService(String baseUri) {
		super(baseUri);
	}

	@Override
	public TypeReference<Language> getGenericType() {
		return new TypeReference<Language>() {};
	}

	@Override
	public TypeReference<List<Language>> getGenericListType() {
		return new TypeReference<List<Language>>() {};
	}

	@Override
	protected String getPath() {
		return "/languages";
	}

}
