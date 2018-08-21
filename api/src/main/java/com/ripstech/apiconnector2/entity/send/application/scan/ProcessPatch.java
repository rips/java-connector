package com.ripstech.apiconnector2.entity.send.application.scan;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.OffsetDateTime;
import java.util.Optional;

@JsonRootName("process")
public class ProcessPatch {

	Optional<Integer> pid;
	Optional<OffsetDateTime> finish;
	Optional<String> version;
	Optional<String> name;
	Optional<Boolean> finished;

	static ProcessPatch createPatch() {
		return new ProcessPatch();
	}

	private ProcessPatch() {}

	public Optional<Integer> getPid() {
		return pid;
	}

	public Optional<OffsetDateTime> getFinish() {
		return finish;
	}

	public Optional<String> getVersion() {
		return version;
	}

	public Optional<String> getName() {
		return name;
	}

	public Optional<Boolean> getFinished() {
		return finished;
	}

	public ProcessPatch setPid(Integer pid) {
		this.pid = Optional.ofNullable(pid);
		return this;
	}

	public ProcessPatch setFinish(OffsetDateTime finish) {
		this.finish = Optional.ofNullable(finish);
		return this;
	}

	public ProcessPatch setVersion(String version) {
		this.version = Optional.ofNullable(version);
		return this;
	}

	public ProcessPatch setName(String name) {
		this.name = Optional.ofNullable(name);
		return this;
	}

	public ProcessPatch setFinished(Boolean finished) {
		this.finished = Optional.ofNullable(finished);
		return this;
	}
}
