package com.ripstech.apiconnector2.entity.receive;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

public class Organisation {

	private int id;
	private String name;
	private List<Quota> quotas = Collections.emptyList();
	private OffsetDateTime validUntil;
	private List<String> trialIssueTypes = Collections.emptyList();
	private List<String> callbacks = Collections.emptyList();

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public List<Quota> getQuotas() {
		return this.quotas;
	}

	public OffsetDateTime getValidUntil() {
		return this.validUntil;
	}

	public List<String> getTrialIssueTypes() {
		return this.trialIssueTypes;
	}

	public List<String> getCallbacks() {
		return this.callbacks;
	}

}
