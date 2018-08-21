package com.ripstech.apiconnector2.entity.receive.application;

import com.ripstech.apiconnector2.entity.receive.Application;
import com.ripstech.apiconnector2.entity.receive.User;

import java.time.OffsetDateTime;

public class Upload {

	private long id;
	private OffsetDateTime submission;
	private String name;
	private String file;
	private String extension;
	private Integer size;
	private User createdBy;
	private Application application;

	public long getId() {
		return this.id;
	}

	public OffsetDateTime getSubmission() {
		return this.submission;
	}

	public String getName() {
		return this.name;
	}

	public String getFile() {
		return this.file;
	}

	public String getExtension() {
		return this.extension;
	}

	public Integer getSize() {
		return this.size;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

	public Application getApplication() {
		return this.application;
	}

}
