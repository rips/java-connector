package com.ripstech.apiconnector2.entity.send.application.scan;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.OffsetDateTime;
import java.util.Optional;

@JsonRootName("process")
public class ProcessPatch {

	Optional<OffsetDateTime> finish;

	static ProcessPatch createPatch() {
		return new ProcessPatch();
	}

	private ProcessPatch() {}

	public Optional<OffsetDateTime> getFinish() {
		return finish;
	}

	public ProcessPatch setFinish(OffsetDateTime finish) {
		this.finish = Optional.ofNullable(finish);
		return this;
	}
}
