package com.ripstech.apiconnector2.entity.receive.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ripstech.apiconnector2.entity.receive.Application;
import com.ripstech.apiconnector2.entity.receive.application.custom.*;

import java.util.Collections;
import java.util.List;

public class Custom {

	private int id;
	private String name;
	private Boolean global;
	@JsonProperty("default")
	private Boolean default_;
	private List<Sink> sinks = Collections.emptyList();
	private List<Ignore> ignores = Collections.emptyList();
	private List<Validator> validators = Collections.emptyList();
	private Application application;
	private List<Sanitiser> sanitisers = Collections.emptyList();
	private List<Source> sources = Collections.emptyList();
	private Setting setting;

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Boolean getGlobal() {
		return this.global;
	}

	public Boolean getDefault() {
		return this.default_;
	}

	public List<Sink> getSinks() {
		return this.sinks;
	}

	public List<Ignore> getIgnores() {
		return this.ignores;
	}

	public List<Validator> getValidators() {
		return this.validators;
	}

	public Application getApplication() {
		return this.application;
	}

	public List<Sanitiser> getSanitisers() {
		return this.sanitisers;
	}

	public List<Source> getSources() {
		return this.sources;
	}

	public Setting getSetting() {
		return setting;
	}

}
