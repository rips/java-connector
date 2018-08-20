package com.ripstech.apiconnector2.entity.receive;

import java.time.OffsetDateTime;

public class License {

	private long id;
	private OffsetDateTime submission;
	private OffsetDateTime validUntil;
	private Boolean quotaDistributed;
	private Organisation organisation;
	private User createdBy;

	public long getId() {
		return this.id;
	}

	public OffsetDateTime getSubmission() {
		return this.submission;
	}

	public OffsetDateTime getValidUntil() {
		return this.validUntil;
	}

	public Boolean getQuotaDistributed() {
		return this.quotaDistributed;
	}

	public Organisation getOrganisation() {
		return this.organisation;
	}

	public User getCreatedBy() {
		return createdBy;
	}

}
