package com.ripstech.apiconnector2.entity.receive;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

public class User {

	private long id;
	private String username;
	private Boolean emptyUsername;
	private String email;
	private Boolean emailConfirmed;
	private OffsetDateTime lastLogin;
	private List<String> roles = Collections.emptyList();
	private String firstname;
	private String lastname;
	private Boolean enabled;
	private Boolean root;
	private List<String> callbacks = Collections.emptyList();
	private List<String> whitelistedIps = Collections.emptyList();
	private Organisation organisation;

	public long getId() {
		return this.id;
	}

	public String getUsername() {
		return this.username;
	}

	public Boolean getEmptyUsername() {
		return this.emptyUsername;
	}

	public String getEmail() {
		return this.email;
	}

	public Boolean getEmailConfirmed() {
		return this.emailConfirmed;
	}

	public OffsetDateTime getLastLogin() {
		return this.lastLogin;
	}

	public List<String> getRoles() {
		return this.roles;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public Boolean getRoot() {
		return this.root;
	}

	public List<String> getCallbacks() {
		return this.callbacks;
	}

	public List<String> getWhitelistedIps() {
		return this.whitelistedIps;
	}

	public Organisation getOrganisation() {
		return this.organisation;
	}

}
