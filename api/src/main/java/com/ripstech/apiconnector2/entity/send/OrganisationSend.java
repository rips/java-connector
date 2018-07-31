package com.ripstech.apiconnector2.entity.send;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@JsonRootName(value = "organisation")
public class OrganisationSend {

	private Optional<String> name;
	private Optional<OffsetDateTime> validUntil;
	private Optional<List<String>> trialIssueTypes;

	public static OrganisationSend createPost(String name) {
		return new OrganisationSend().setName(name);
	}

	public static OrganisationSend createPatch() {
		return new OrganisationSend();
	}

	private OrganisationSend() {}

	public OrganisationSend setName(String name) {
		this.name = Optional.ofNullable(name);
		return this;
	}

	public OrganisationSend setValidUntil(OffsetDateTime validUntil) {
		this.validUntil = Optional.ofNullable(validUntil);
		return this;
	}

	public OrganisationSend setTrialIssueTypes(List<String> trialIssueTypes) {
		this.trialIssueTypes = Optional.ofNullable(trialIssueTypes);
		return this;
	}

	public Optional<String> getName() {
		return this.name;
	}

	public Optional<OffsetDateTime> getValidUntil() {
		return this.validUntil;
	}

	public Optional<List<String>> getTrialIssueTypes() {
		return this.trialIssueTypes;
	}
}
