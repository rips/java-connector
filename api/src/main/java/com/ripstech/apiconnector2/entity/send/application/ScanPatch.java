package com.ripstech.apiconnector2.entity.send.application;

import com.ripstech.apiconnector2.Phase;

import java.util.List;
import java.util.Optional;

public class ScanPatch {

	private Optional<ScanSub> scan;
	private Optional<List<String>> tags;

	static ScanPatch createPatch() {
		return new ScanPatch();
	}

	public ScanPatch setScan(ScanSub scan) {
		this.scan = Optional.ofNullable(scan);
		return this;
	}

	public ScanPatch setTags(List<String> tags) {
		this.tags = Optional.ofNullable(tags);
		return this;
	}

	private ScanPatch() {}

	public Optional<ScanSub> getScan() {
		return this.scan;
	}

	public Optional<List<String>> getTags() {
		return this.tags;
	}

	public static class ScanSub {

		private Optional<Integer> phase;
		private Optional<Integer> percent;
		private Optional<Integer> log;
		private Optional<String> version;
		private Optional<List<String>> callbacks;
		private Optional<Boolean> extendedCallbacks;
		private Optional<String> comment;

		private ScanSub() {}

		public static ScanSub createPatch() {
			return new ScanSub();
		}

		public ScanSub setPhase(Integer phase) {
			this.phase = Optional.ofNullable(phase);
			return this;
		}

		public ScanSub setPhase(Phase phase) {
			this.phase = Optional.of(phase.getPhase());
			return this;
		}

		public ScanSub setPercent(Integer percent) {
			this.percent = Optional.ofNullable(percent);
			return this;
		}

		public ScanSub setLog(Integer log) {
			this.log = Optional.ofNullable(log);
			return this;
		}

		public ScanSub setVersion(String version) {
			this.version = Optional.ofNullable(version);
			return this;
		}

		public ScanSub setCallbacks(Optional<List<String>> callbacks) {
			this.callbacks = callbacks;
			return this;
		}

		public ScanSub setExtendedCallbacks(Boolean extendedCallbacks) {
			this.extendedCallbacks = Optional.ofNullable(extendedCallbacks);
			return this;
		}

		public ScanSub setComment(String comment) {
			this.comment = Optional.ofNullable(comment);
			return this;
		}

		public Optional<Integer> getPhase() {
			return this.phase;
		}

		public Optional<Integer> getPercent() {
			return this.percent;
		}

		public Optional<Integer> getLog() {
			return this.log;
		}

		public Optional<String> getVersion() {
			return this.version;
		}

		public Optional<List<String>> getCallbacks() {
			return callbacks;
		}

		public Optional<Boolean> getExtendedCallbacks() {
			return extendedCallbacks;
		}

		public Optional<String> getComment() {
			return comment;
		}
	}

}
