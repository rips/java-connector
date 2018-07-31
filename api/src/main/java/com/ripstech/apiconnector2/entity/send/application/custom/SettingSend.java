package com.ripstech.apiconnector2.entity.send.application.custom;

import com.ripstech.apiconnector2.entity.receive.application.scan.issue.Type;
import com.ripstech.apiconnector2.entity.send.application.scan.PhpSend;

import java.util.List;

public class SettingSend {

	private ApplicationCustomSettingSubSend setting;
	private PhpSend php;

	private SettingSend() {}

	public static SettingSend createPut() {
		return new SettingSend();
	}

	public ApplicationCustomSettingSubSend getSetting() {
		return setting;
	}

	public SettingSend setSetting(ApplicationCustomSettingSubSend setting) {
		this.setting = setting;
		return this;
	}

	public PhpSend getPhp() {
		return php;
	}

	public SettingSend setPhp(PhpSend php) {
		this.php = php;
		return this;
	}

	public static class ApplicationCustomSettingSubSend {
		private List<Type> issueTypes;
		private Boolean codeStored;
		private Boolean uploadRemoved;
		private Boolean fullCodeCompared;
		private Boolean historyInherited;
		private Integer analysisDepth;
		private Integer maxIssuesPerType;

		private ApplicationCustomSettingSubSend() {}

		private static ApplicationCustomSettingSubSend createPut() {
			return new ApplicationCustomSettingSubSend();
		}

		public List<Type> getIssueTypes() {
			return issueTypes;
		}

		public ApplicationCustomSettingSubSend setIssueTypes(List<Type> issueTypes) {
			this.issueTypes = issueTypes;
			return this;
		}

		public Boolean getCodeStored() {
			return codeStored;
		}

		public ApplicationCustomSettingSubSend setCodeStored(Boolean codeStored) {
			this.codeStored = codeStored;
			return this;
		}

		public Boolean getUploadRemoved() {
			return uploadRemoved;
		}

		public ApplicationCustomSettingSubSend setUploadRemoved(Boolean uploadRemoved) {
			this.uploadRemoved = uploadRemoved;
			return this;
		}

		public Boolean getFullCodeCompared() {
			return fullCodeCompared;
		}

		public ApplicationCustomSettingSubSend setFullCodeCompared(Boolean fullCodeCompared) {
			this.fullCodeCompared = fullCodeCompared;
			return this;
		}

		public Boolean getHistoryInherited() {
			return historyInherited;
		}

		public ApplicationCustomSettingSubSend setHistoryInherited(Boolean historyInherited) {
			this.historyInherited = historyInherited;
			return this;
		}

		public Integer getAnalysisDepth() {
			return analysisDepth;
		}

		public ApplicationCustomSettingSubSend setAnalysisDepth(Integer analysisDepth) {
			this.analysisDepth = analysisDepth;
			return this;
		}

		public Integer getMaxIssuesPerType() {
			return maxIssuesPerType;
		}

		public ApplicationCustomSettingSubSend setMaxIssuesPerType(Integer maxIssuesPerType) {
			this.maxIssuesPerType = maxIssuesPerType;
			return this;
		}
	}

}
