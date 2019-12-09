package com.ripstech.api.connector.service.application.scan.issue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.connector.service.template.DeletePostGetService;
import com.ripstech.api.entity.receive.application.scan.issue.Comment;
import com.ripstech.api.entity.send.application.scan.issue.CommentSend;

import java.util.List;

public class CommentService extends DeletePostGetService<Comment, CommentSend> {

	private final long applicationId;
	private final long scanId;
	private final long issueId;

	public CommentService(String baseUri, long applicationId, long scanId, long issueId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
		this.issueId = issueId;
	}

	@Override
	public TypeReference<Comment> getGenericType() {
		return new TypeReference<Comment>() {};
	}

	@Override
	public TypeReference<List<Comment>> getGenericListType() {
		return new TypeReference<List<Comment>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/issues/%d/comments", applicationId, scanId, issueId);
	}

}
