package com.ripstech.apiconnector2.entity.send;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "license")
public class LicenseSend {

	private String key;
	private Boolean quotaDistributed;

	public static LicenseSend createPost(String key) {
		return new LicenseSend().setKey(key);
	}

	private LicenseSend() {}

	public String getKey() {
		return key;
	}

	public LicenseSend setKey(String key) {
		this.key = key;
		return this;
	}

	public Boolean getQuotaDistributed() {
		return quotaDistributed;
	}

	public LicenseSend setQuotaDistributed(Boolean quotaDistributed) {
		this.quotaDistributed = quotaDistributed;
		return this;
	}
}
