package com.ripstech.apiconnector2.entity.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public class Quota {

	private long id;
	private Integer currentApplication;
	private Integer currentScan;
	private Integer currentUser;
	private Integer maxApplications;
	private Integer maxScans;
	private Integer maxUsers;
	private Integer maxLoc;
	private OffsetDateTime validFrom;
	private OffsetDateTime validUntil;
	private Integer allowedMisses;
	@JsonProperty("public")
	private Boolean public_;
	private Organisation organisation;
	private Boolean notify;

	public long getId() {
		return this.id;
	}

	public Integer getCurrentApplication() {
		return this.currentApplication;
	}

	public Integer getCurrentScan() {
		return this.currentScan;
	}

	public Integer getCurrentUser() {
		return this.currentUser;
	}

	public Integer getMaxApplications() {
		return this.maxApplications;
	}

	public Integer getMaxScans() {
		return this.maxScans;
	}

	public Integer getMaxUsers() {
		return this.maxUsers;
	}

	public Integer getMaxLoc() {
		return this.maxLoc;
	}

	public OffsetDateTime getValidFrom() {
		return this.validFrom;
	}

	public OffsetDateTime getValidUntil() {
		return this.validUntil;
	}

	public Integer getAllowedMisses() {
		return this.allowedMisses;
	}

	public Boolean getPublic_() {
		return this.public_;
	}

	public Organisation getOrganisation() {
		return this.organisation;
	}

	public Boolean getNotify() {
		return this.notify;
	}

}
