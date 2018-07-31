package com.ripstech.apiconnector2.entity.send;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.OffsetDateTime;
import java.util.Optional;

@JsonRootName(value = "quota")
public class QuotaSend {

	private Optional<Integer> currentApplication;
	private Optional<Integer> currentScan;
	private Optional<Integer> currentUser;
	private Optional<Integer> maxApplications;
	private Optional<Integer> maxScans;
	private Optional<Integer> maxUsers;
	private Optional<Integer> maxLoc;
	private Optional<OffsetDateTime> validFrom;
	private Optional<OffsetDateTime> validUntil;
	private Optional<Integer> allowedMisses;
	@JsonProperty("public")
	private Optional<Boolean> public_;
	private Optional<Integer> organisation;
	private Optional<Boolean> notify;

	public static QuotaSend createPost(OffsetDateTime validFrom, OffsetDateTime validUntil, Integer organisation) {
		return new QuotaSend().setValidFrom(validFrom).setValidUntil(validUntil).setOrganisation(organisation);
	}

	public static QuotaSend createPatch() {
		return new QuotaSend();
	}

	private QuotaSend() {}

	public QuotaSend setCurrentApplication(Integer currentApplication) {
		this.currentApplication = Optional.ofNullable(currentApplication);
		return this;
	}

	public QuotaSend setCurrentScan(Integer currentScan) {
		this.currentScan = Optional.ofNullable(currentScan);
		return this;
	}

	public QuotaSend setCurrentUser(Integer currentUser) {
		this.currentUser = Optional.ofNullable(currentUser);
		return this;
	}

	public QuotaSend setMaxApplications(Integer maxApplications) {
		this.maxApplications = Optional.ofNullable(maxApplications);
		return this;
	}

	public QuotaSend setMaxScans(Integer maxScans) {
		this.maxScans = Optional.ofNullable(maxScans);
		return this;
	}

	public QuotaSend setMaxUsers(Integer maxUsers) {
		this.maxUsers = Optional.ofNullable(maxUsers);
		return this;
	}

	public QuotaSend setMaxLoc(Integer maxLoc) {
		this.maxLoc = Optional.ofNullable(maxLoc);
		return this;
	}

	public QuotaSend setValidFrom(OffsetDateTime validFrom) {
		this.validFrom = Optional.ofNullable(validFrom);
		return this;
	}

	public QuotaSend setValidUntil(OffsetDateTime validUntil) {
		this.validUntil = Optional.ofNullable(validUntil);
		return this;
	}

	public QuotaSend setAllowedMisses(Integer allowedMisses) {
		this.allowedMisses = Optional.ofNullable(allowedMisses);
		return this;
	}

	public QuotaSend setPublic_(Boolean isPublic) {
		this.public_ = Optional.ofNullable(isPublic);
		return this;
	}

	public QuotaSend setOrganisation(Integer organisation) {
		this.organisation = Optional.ofNullable(organisation);
		return this;
	}

	public QuotaSend setNotify(Boolean notify) {
		this.notify = Optional.ofNullable(notify);
		return this;
	}

	public Optional<Integer> getCurrentApplication() {
		return this.currentApplication;
	}

	public Optional<Integer> getCurrentScan() {
		return this.currentScan;
	}

	public Optional<Integer> getCurrentUser() {
		return this.currentUser;
	}

	public Optional<Integer> getMaxApplications() {
		return this.maxApplications;
	}

	public Optional<Integer> getMaxScans() {
		return this.maxScans;
	}

	public Optional<Integer> getMaxUsers() {
		return this.maxUsers;
	}

	public Optional<Integer> getMaxLoc() {
		return this.maxLoc;
	}

	public Optional<OffsetDateTime> getValidFrom() {
		return this.validFrom;
	}

	public Optional<OffsetDateTime> getValidUntil() {
		return this.validUntil;
	}

	public Optional<Integer> getAllowedMisses() {
		return this.allowedMisses;
	}

	public Optional<Boolean> getPublic_() {
		return this.public_;
	}

	public Optional<Integer> getOrganisation() {
		return this.organisation;
	}

	public Optional<Boolean> getNotify() {
		return this.notify;
	}
}
