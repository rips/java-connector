package com.ripstech.apiconnector2.constant;

@SuppressWarnings("unused")
public enum CallbackReports {

	UPDATE_USER,
	DELETE_USER,
	CREATE_SCAN,
	UPDATE_SCAN,
	FINISH_SCAN,
	CREATE_COMMENT,
	CREATE_REVIEW;

	@Override
	public String toString() {
		return this.name().toUpperCase();
	}

}
