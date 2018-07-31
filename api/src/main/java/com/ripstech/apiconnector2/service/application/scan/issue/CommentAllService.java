package com.ripstech.apiconnector2.service.application.scan.issue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.Comment;
import com.ripstech.apiconnector2.service.template.SimpleGetListService;

import java.util.List;

public class CommentAllService extends SimpleGetListService<Comment> {

	public CommentAllService(String baseUri) {
		super(baseUri);
	}

	@Override
	public TypeReference<List<Comment>> getGenericListType() {
		return new TypeReference<List<Comment>>() {};
	}

	@Override
	protected String getPath() {
		return "applications/scans/issues/comments/all";
	}
}
