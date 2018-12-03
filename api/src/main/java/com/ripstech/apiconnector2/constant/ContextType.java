package com.ripstech.apiconnector2.constant;

@SuppressWarnings("unused")
public enum ContextType {
	TEXT,
	INJECTION;

	@Override
	public String toString() {
		return this.name().toUpperCase();
	}
}
