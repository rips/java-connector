package com.ripstech.apiconnector2.entity.send.application;

import com.ripstech.apiconnector2.entity.send.application.scan.PhpSend;
import com.ripstech.apiconnector2.entity.send.dsl.Parameter;
import com.ripstech.apiconnector2.entity.send.dsl.ParameterImpl;
import com.ripstech.apiconnector2.entity.send.dsl.RequiredParameter;

import java.util.Arrays;
import java.util.List;

public class ScanPost {

	private ScanSub scan;
	private List<String> tags;
	private PhpSend php;

	static ScanPost createPost(ScanSub scan) {
		return new ScanPost().setScan(scan);
	}

	@SafeVarargs
	public static ScanPost createPost(RequiredParameter<ScanPost> reqParam, Parameter<ScanPost>... params) {
		ScanPost applicationScanPost = new ScanPost();
		reqParam.set(applicationScanPost);
		Arrays.stream(params).forEach(param -> param.set(applicationScanPost));
		return applicationScanPost;
	}

	@SafeVarargs
	public static ScanPost createPost(ScanSub scan, Parameter<ScanPost>... params) {
		ScanPost applicationScanPost = new ScanPost().setScan(scan);
		Arrays.stream(params).forEach(param -> param.set(applicationScanPost));
		return applicationScanPost;
	}

	private ScanPost() {}

	public ScanSub getScan() {
		return this.scan;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public PhpSend getPhp() {
		return this.php;
	}

	public static RequiredParameter<ScanPost> scan(ScanSub scan) {
		return new ParameterImpl<>(scan, ScanPost::setScan);
	}

	@SafeVarargs
	public static RequiredParameter<ScanPost> scan(String version, Parameter<ScanSub>... scanParams) {
		return new ParameterImpl<>(ScanSub.createPost(version, scanParams), ScanPost::setScan);
	}

	public ScanPost setScan(ScanSub scan) {
		this.scan = scan;
		return this;
	}

	public static Parameter<ScanPost> tags(List<String> tags) {
		return new ParameterImpl<>(tags, ScanPost::setTags);
	}

	public ScanPost setTags(List<String> tags) {
		this.tags = tags;
		return this;
	}

	public static Parameter<ScanPost> php(PhpSend php) {
		return new ParameterImpl<>(php, ScanPost::setPhp);
	}

	@SafeVarargs
	public static Parameter<ScanPost> php(Parameter<PhpSend>... phpParams) {
		return new ParameterImpl<>(PhpSend.createPost(phpParams), ScanPost::setPhp);
	}

	public ScanPost setPhp(PhpSend php) {
		this.php = php;
		return this;
	}

	public static class ScanSub {

		private Integer chargedQuota;
		private Integer custom;
		private Integer parent;
		private Boolean codeStored;
		private Boolean uploadRemoved;
		private Boolean historyInherited;
		private List<Integer> issueTypes;
		private String version;
		private Integer upload;
		private String path;
		private Integer analysisDepth;
		private List<String> callbacks;
		private Boolean extendedCallbacks;
		private Integer maxIssuesPerType;
		private String comment;

		public static ScanSub createPost(String version) {
			return new ScanSub().setVersion(version);
		}

		@SafeVarargs
		public static ScanSub createPost(String version, Parameter<ScanSub>... params) {
			ScanSub applicationScanSubPost = new ScanSub().setVersion(version);
			Arrays.stream(params).forEach(param -> param.set(applicationScanSubPost));
			return applicationScanSubPost;
		}

		private ScanSub() {}

		public Integer getChargedQuota() {
			return this.chargedQuota;
		}

		public Integer getCustom() {
			return this.custom;
		}

		public Integer getParent() {
			return this.parent;
		}

		public Boolean getCodeStored() {
			return this.codeStored;
		}

		public Boolean getUploadRemoved() {
			return this.uploadRemoved;
		}

		public Boolean getHistoryInherited() {
			return this.historyInherited;
		}

		public List<Integer> getIssueTypes() {
			return this.issueTypes;
		}

		public String getVersion() {
			return this.version;
		}

		public Integer getUpload() {
			return upload;
		}

		public String getPath() {
			return this.path;
		}

		public Integer getAnalysisDepth() {
			return this.analysisDepth;
		}

		public List<String> getCallbacks() {
			return this.callbacks;
		}

		public Boolean getExtendedCallbacks() {
			return extendedCallbacks;
		}

		public Integer getMaxIssuesPerType() {
			return maxIssuesPerType;
		}

		public String getComment() {
			return comment;
		}

		public static Parameter<ScanSub> chargedQuota(Integer chargedQuota) {
			return new ParameterImpl<>(chargedQuota, ScanSub::setChargedQuota);
		}

		public ScanSub setChargedQuota(Integer chargedQuota) {
			this.chargedQuota = chargedQuota;
			return this;
		}

		public static Parameter<ScanSub> custom(Integer custom) {
			return new ParameterImpl<>(custom, ScanSub::setCustom);
		}

		public ScanSub setCustom(Integer custom) {
			this.custom = custom;
			return this;
		}

		public static Parameter<ScanSub> parent(Integer parent) {
			return new ParameterImpl<>(parent, ScanSub::setParent);
		}

		public ScanSub setParent(Integer parent) {
			this.parent = parent;
			return this;
		}

		public static Parameter<ScanSub> codeStored(Boolean codeStored) {
			return new ParameterImpl<>(codeStored, ScanSub::setCodeStored);
		}

		public ScanSub setCodeStored(Boolean codeStored) {
			this.codeStored = codeStored;
			return this;
		}

		public static Parameter<ScanSub> uploadRemoved(Boolean uploadRemoved) {
			return new ParameterImpl<>(uploadRemoved, ScanSub::setUploadRemoved);
		}

		public ScanSub setUploadRemoved(Boolean uploadRemoved) {
			this.uploadRemoved = uploadRemoved;
			return this;
		}

		public static Parameter<ScanSub> historyInherited(Boolean historyInherited) {
			return new ParameterImpl<>(historyInherited, ScanSub::setHistoryInherited);
		}

		public ScanSub setHistoryInherited(Boolean historyInherited) {
			this.historyInherited = historyInherited;
			return this;
		}

		public static Parameter<ScanSub> historyInherited(List<Integer> issueTypes) {
			return new ParameterImpl<>(issueTypes, ScanSub::setIssueTypes);
		}

		public ScanSub setIssueTypes(List<Integer> issueTypes) {
			this.issueTypes = issueTypes;
			return this;
		}

		public static Parameter<ScanSub> version(String version) {
			return new ParameterImpl<>(version, ScanSub::setVersion);
		}

		public ScanSub setVersion(String version) {
			this.version = version;
			return this;
		}

		public static Parameter<ScanSub> upload(Integer upload) {
			return new ParameterImpl<>(upload, ScanSub::setUpload);
		}

		public ScanSub setUpload(Integer upload) {
			this.upload = upload;
			return this;
		}

		public static Parameter<ScanSub> path(String path) {
			return new ParameterImpl<>(path, ScanSub::setPath);
		}

		public ScanSub setPath(String path) {
			this.path = path;
			return this;
		}

		public static Parameter<ScanSub> analysisDepth(Integer analysisDepth) {
			return new ParameterImpl<>(analysisDepth, ScanSub::setAnalysisDepth);
		}

		public ScanSub setAnalysisDepth(Integer analysisDepth) {
			this.analysisDepth = analysisDepth;
			return this;
		}

		public static Parameter<ScanSub> callbacks(List<String> callbacks) {
			return new ParameterImpl<>(callbacks, ScanSub::setCallbacks);
		}

		public ScanSub setCallbacks(List<String> callbacks) {
			this.callbacks = callbacks;
			return this;
		}

		public static Parameter<ScanSub> extendedCallbacks(Boolean extendedCallbacks) {
			return new ParameterImpl<>(extendedCallbacks, ScanSub::setExtendedCallbacks);
		}

		public ScanSub setExtendedCallbacks(Boolean extendedCallbacks) {
			this.extendedCallbacks = extendedCallbacks;
			return this;
		}

		public static Parameter<ScanSub> maxIssuesPerType(Integer maxIssuesPerType) {
			return new ParameterImpl<>(maxIssuesPerType, ScanSub::setMaxIssuesPerType);
		}

		public ScanSub setMaxIssuesPerType(Integer maxIssuesPerType) {
			this.maxIssuesPerType = maxIssuesPerType;
			return this;
		}

		public static Parameter<ScanSub> comment(String comment) {
			return new ParameterImpl<>(comment, ScanSub::setComment);
		}

		public ScanSub setComment(String comment) {
			this.comment = comment;
			return this;
		}
	}

}
