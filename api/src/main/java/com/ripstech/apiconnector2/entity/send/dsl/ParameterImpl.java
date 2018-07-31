package com.ripstech.apiconnector2.entity.send.dsl;

import java.util.function.BiConsumer;

public class ParameterImpl<K, T> implements Parameter<T>,RequiredParameter<T> {

	final K var;
	final BiConsumer<T, K> setConsumer;

	public ParameterImpl(K var, BiConsumer<T, K> setConsumer) {
		this.var = var;
		this.setConsumer = setConsumer;
	}

	@Override
	public void set(T obj) {
		setConsumer.accept(obj, var);
	}

}
