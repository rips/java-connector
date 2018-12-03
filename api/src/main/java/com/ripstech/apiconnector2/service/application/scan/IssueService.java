package com.ripstech.apiconnector2.service.application.scan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ripstech.api.entity.receive.application.scan.Issue;
import com.ripstech.api.entity.receive.application.scan.issue.Stats;
import com.ripstech.api.entity.send.application.scan.IssueSend;
import com.ripstech.api.entity.send.application.scan.issue.*;
import com.ripstech.apiconnector2.ApiResponse;
import com.ripstech.apiconnector2.service.queryparameter.Filter;
import com.ripstech.apiconnector2.service.template.PostGetService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.ripstech.apiconnector2.service.template.GenericService.HttpMethod.GET;

public class IssueService extends PostGetService<Issue, IssueService.IssueSendPost> {

	private final long applicationId;
	private final long scanId;

	public IssueService(String baseUri, long applicationId, long scanId) {
		super(baseUri);
		this.applicationId = applicationId;
		this.scanId = scanId;
		withRootName = false;
	}

	@Override
	public TypeReference<Issue> getGenericType() {
		return new TypeReference<Issue>() {};
	}

	@Override
	public TypeReference<List<Issue>> getGenericListType() {
		return new TypeReference<List<Issue>>() {};
	}

	@Override
	protected String getPath() {
		return String.format("applications/%d/scans/%d/issues", applicationId, scanId);
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

	public ApiResponse<List<Issue>> get(Filter filter, boolean minimal) {
		return new ApiResponse<>(getTarget(GET)
				                         .addQueryParams(filter)
				                         .addQueryParam("minimal", minimal),
		                         getGenericListType());
	}

	public ApiResponse<List<Issue>> get(boolean minimal) {
		return get(Filter.empty(), minimal);
	}

	public ApiResponse<Issue> get(int id, boolean minimal) {
		return new ApiResponse<>(getTarget(GET)
				                         .appendPath(id)
				                         .addQueryParam("minimal", minimal),
		                         getGenericType());
	}

	public static class IssueSendPost {
		IssueSend.Post issue;
		SourceSend.Post source;
		TaintSend.Post sourceTaint;
		SinkSend.Post sink;
		TaintSend.Post sinkTaint;
		ConcatSend.Post concat;
		TaintSend.Post concatTaint;
		EntrypointSend.Post entrypoint;
		ContextsSend contexts;
		SummariesSend summaries;

		public IssueSendPost(@NotNull IssueSend.Post issue) {
			this.issue = issue;
		}

		public IssueSend.Post getIssue() {
			return issue;
		}

		public IssueSendPost setIssue(IssueSend.Post issue) {
			this.issue = issue;
			return this;
		}

		public SourceSend.Post getSource() {
			return source;
		}

		public IssueSendPost setSource(SourceSend.Post source) {
			this.source = source;
			return this;
		}

		public TaintSend.Post getSourceTaint() {
			return sourceTaint;
		}

		public IssueSendPost setSourceTaint(TaintSend.Post sourceTaint) {
			this.sourceTaint = sourceTaint;
			return this;
		}

		public SinkSend.Post getSink() {
			return sink;
		}

		public IssueSendPost setSink(SinkSend.Post sink) {
			this.sink = sink;
			return this;
		}

		public TaintSend.Post getSinkTaint() {
			return sinkTaint;
		}

		public IssueSendPost setSinkTaint(TaintSend.Post sinkTaint) {
			this.sinkTaint = sinkTaint;
			return this;
		}

		public ConcatSend.Post getConcat() {
			return concat;
		}

		public IssueSendPost setConcat(ConcatSend.Post concat) {
			this.concat = concat;
			return this;
		}

		public TaintSend.Post getConcatTaint() {
			return concatTaint;
		}

		public IssueSendPost setConcatTaint(TaintSend.Post concatTaint) {
			this.concatTaint = concatTaint;
			return this;
		}

		public EntrypointSend.Post getEntrypoint() {
			return entrypoint;
		}

		public IssueSendPost setEntrypoint(EntrypointSend.Post entrypoint) {
			this.entrypoint = entrypoint;
			return this;
		}

		public ContextsSend getContexts() {
			return contexts;
		}

		public IssueSendPost setContexts(ContextsSend contexts) {
			this.contexts = contexts;
			return this;
		}

		public SummariesSend getSummaries() {
			return summaries;
		}

		public IssueSendPost setSummaries(SummariesSend summaries) {
			this.summaries = summaries;
			return this;
		}
	}

}
