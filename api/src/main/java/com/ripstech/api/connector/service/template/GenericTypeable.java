package com.ripstech.api.connector.service.template;

import com.fasterxml.jackson.core.type.TypeReference;

public interface GenericTypeable<T> {

	TypeReference<T> getGenericType();

}
