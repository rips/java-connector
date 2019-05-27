package com.ripstech.api.connector.service.application.scan.issue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.PostGetService;
import com.ripstech.api.entity.receive.application.scan.issue.Patch;
import com.ripstech.api.entity.send.application.scan.issue.PatchSend;
import com.ripstech.api.entity.send.application.scan.issue.patch.ModificationSend;

import java.util.List;

public class PatchService extends PostGetService<Patch, PatchService.PatchSendPost> {

	private final long applicationId;
	private final long scanId;
	private final long issueId;

	public PatchService(String baseUri, long applicationId, long scanId, long issueId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
		this.issueId = issueId;
		withRootName = false;
	}

	@Override
	public TypeReference<Patch> getGenericType() {
		return new TypeReference<Patch>() {};
	}

	@Override
	public TypeReference<List<Patch>> getGenericListType() {
		return new TypeReference<List<Patch>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/issues/%d/patches", applicationId, scanId, issueId);
	}

	public static class PatchSendPost {
		private PatchSend patch;
		private List<ModificationSend> modifications;

		public PatchSendPost(PatchSend patch) {
			this.patch = patch;
		}

		public PatchSend getPatch() {
			return patch;
		}

		public PatchSendPost setPatch(PatchSend patch) {
			this.patch = patch;
			return this;
		}

		public List<ModificationSend> getModifications() {
			return modifications;
		}

		public PatchSendPost setModifications(List<ModificationSend> modifications) {
			this.modifications = modifications;
			return this;
		}
	}
}
