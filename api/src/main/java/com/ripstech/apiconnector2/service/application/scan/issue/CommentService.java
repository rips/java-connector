package com.ripstech.apiconnector2.service.application.scan.issue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.Comment;
import com.ripstech.apiconnector2.entity.send.application.scan.issue.CommentSend;
import com.ripstech.apiconnector2.service.template.DeletePostGetService;

import java.util.List;

public class CommentService extends DeletePostGetService<Comment, CommentSend> {

	private final int applicationId;
	private final int scanId;
	private final int issueId;

	public CommentService(String baseUri, int applicationId, int scanId, int issueId) {
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
