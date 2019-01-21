package com.ripstech.api.connector.service.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.ApiResponse;
import com.ripstech.api.connector.service.queryparameter.Filter;
import com.ripstech.api.connector.service.template.DeletePostGetService;
import com.ripstech.api.entity.receive.application.Scan;
import com.ripstech.api.entity.receive.application.scan.Stats;
import com.ripstech.api.entity.send.application.ScanSend;
import com.ripstech.api.entity.send.application.scan.JavaSend;
import com.ripstech.api.entity.send.application.scan.LibrarySend;
import com.ripstech.api.entity.send.application.scan.PhpSend;

import java.util.List;
import java.util.Optional;

import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.GET;
import static com.ripstech.api.connector.service.template.GenericService.HttpMethod.PATCH;

public class ScanService extends DeletePostGetService<Scan, ScanService.ScanSendPost> {

	private final long applicationId;

	public ScanService(String baseUri, long applicationId) {
		super(baseUri);
		this.applicationId = applicationId;
		withRootName = false;
	}

	@Override
	public TypeReference<Scan> getGenericType() {
		return new TypeReference<Scan>() {};
	}

	@Override
	public TypeReference<List<Scan>> getGenericListType() {
		return new TypeReference<List<Scan>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans", applicationId);
	}

	public ApiResponse<Stats> getStats(Filter filter) {
		return new ApiResponse<>(getTarget(GET)
				                         .appendPath("stats")
				                         .addQueryParams(filter),
		                         Stats.class);
	}

	public ApiResponse<Stats> getStats() {
		return getStats(Filter.empty());
	}

	public ApiResponse<Scan> patch(long scanId, ScanSendPatch scan) {
		return new ApiResponse<>(getTarget(PATCH)
				                         .appendPath(scanId)
				                         .setJsonBody(scan),
		                         Scan.class);
	}

	public static class ScanSendPost {
		private ScanSend.Post scan;
		private List<String> tags;
		private PhpSend php;
		private JavaSend java;
		private List<LibrarySend> libraries;

		public ScanSendPost(ScanSend.Post scan) {
			this.scan = scan;
		}

		public ScanSend getScan() {
			return scan;
		}

		public ScanSendPost setScan(ScanSend.Post scan) {
			this.scan = scan;
			return this;
		}

		public List<String> getTags() {
			return tags;
		}

		public ScanSendPost setTags(List<String> tags) {
			this.tags = tags;
			return this;
		}

		public PhpSend getPhp() {
			return php;
		}

		public ScanSendPost setPhp(PhpSend php) {
			this.php = php;
			return this;
		}

		public JavaSend getJava() {
			return java;
		}

		public ScanSendPost setJava(JavaSend java) {
			this.java = java;
			return this;
		}

		public List<LibrarySend> getLibraries() {
			return libraries;
		}

		public ScanSendPost setLibraries(List<LibrarySend> libraries) {
			this.libraries = libraries;
			return this;
		}
	}

	public static class ScanSendPatch {
		private Optional<ScanSend.Patch> scan;
		private Optional<List<String>> tags;

		public Optional<ScanSend.Patch> getScan() {
			return scan;
		}

		public ScanSendPatch setScan(ScanSend.Patch scan) {
			this.scan = Optional.ofNullable(scan);
			return this;
		}

		public Optional<List<String>> getTags() {
			return tags;
		}

		public ScanSendPatch setTags(List<String> tags) {
			this.tags = Optional.ofNullable(tags);
			return this;
		}
	}

}
