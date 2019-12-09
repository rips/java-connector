package com.ripstech.api.connector.constant;

@SuppressWarnings("unused")
public enum ContextType {
	TEXT,
	INJECTION;

	@Override
	public String toString() {
		return this.name().toUpperCase();
	}
}
