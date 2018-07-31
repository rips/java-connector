package com.ripstech.apiconnector2.service.application.scan.issue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.Review;
import com.ripstech.apiconnector2.entity.send.application.scan.issue.ReviewSend;
import com.ripstech.apiconnector2.service.template.PostGetService;

import java.util.List;

public class ReviewService extends PostGetService<Review, ReviewSend> {

	private final int applicationId;
	private final int scanId;
	private final int issueId;

	public ReviewService(String baseUri, int applicationId, int scanId, int issueId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
		this.issueId = issueId;
	}

	@Override
	public TypeReference<Review> getGenericType() {
		return new TypeReference<Review>() {};
	}

	@Override
	public TypeReference<List<Review>> getGenericListType() {
		return new TypeReference<List<Review>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/issues/%d/reviews", applicationId, scanId, issueId);
	}

}
