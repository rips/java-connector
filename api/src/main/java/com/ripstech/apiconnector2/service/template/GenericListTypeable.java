package com.ripstech.apiconnector2.service.template;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface GenericListTypeable<T> {

	TypeReference<List<T>> getGenericListType();

}
