package com.ripstech.apiconnector2.entity.receive.application.scan;

import com.ripstech.apiconnector2.entity.receive.application.Scan;

import java.time.OffsetDateTime;

public class Process {

	private int id;
	private Integer pid;
	private OffsetDateTime start;
	private OffsetDateTime finish;
	private String version;
	private Scan scan;

	public int getId() {
		return this.id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public OffsetDateTime getStart() {
		return this.start;
	}

	public OffsetDateTime getFinish() {
		return this.finish;
	}

	public String getVersion() {
		return this.version;
	}

	public Scan getScan() {
		return this.scan;
	}

}
