package com.ripstech.apiconnector2.entity.receive;


import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

public class Application {

	private int id;
	private String name;
	private Integer currentScan;
	private User createdBy;
	private Quota chargedQuota;
	private OffsetDateTime creation;
	private Organisation organisation;
	private List<String> defaultScanCallbacks = Collections.emptyList();

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Integer getCurrentScan() {
		return this.currentScan;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

	public Quota getChargedQuota() {
		return this.chargedQuota;
	}

	public OffsetDateTime getCreation() {
		return this.creation;
	}

	public Organisation getOrganisation() {
		return this.organisation;
	}

	public List<String> getDefaultScanCallbacks() {
		return this.defaultScanCallbacks;
	}

}