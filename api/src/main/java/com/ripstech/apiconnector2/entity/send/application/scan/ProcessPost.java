package com.ripstech.apiconnector2.entity.send.application.scan;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("process")
public class ProcessPost {

	private Integer pid;
	private String finish;
	private String version;
	private String name;
	private Boolean finished;

	static ProcessPost createPost(int pid) {
		return new ProcessPost().setPid(pid);
	}

	private ProcessPost() {}

	public Integer getPid() {
		return this.pid;
	}

	public String getFinish() {
		return this.finish;
	}

	public String getVersion() {
		return this.version;
	}

	public String getName() {
		return name;
	}

	public Boolean getFinished() {
		return finished;
	}

	public ProcessPost setPid(Integer pid) {
		this.pid = pid;
		return this;
	}

	public ProcessPost setFinish(String finish) {
		this.finish = finish;
		return this;
	}

	public ProcessPost setVersion(String version) {
		this.version = version;
		return this;
	}

	public ProcessPost setName(String name) {
		this.name = name;
		return this;
	}

	public ProcessPost setFinished(Boolean finished) {
		this.finished = finished;
		return this;
	}
}
