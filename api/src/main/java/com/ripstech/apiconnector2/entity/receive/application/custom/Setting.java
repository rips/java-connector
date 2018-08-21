package com.ripstech.apiconnector2.entity.receive.application.custom;

import com.ripstech.apiconnector2.entity.receive.application.Custom;
import com.ripstech.apiconnector2.entity.receive.application.scan.Php;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.Type;

import java.util.Collections;
import java.util.List;

public class Setting {

	private long id;
	private List<Type> issueTypes = Collections.emptyList();
	private Boolean codeStored;
	private Boolean uploadRemoved;
	private Boolean fullCodeCompared;
	private Boolean historyInherited;
	private Integer analysisDepth;
	private Integer maxIssuesPerType;
	private Php php;
	private Custom custom;

	public long getId() {
		return this.id;
	}

	public List<Type> getIssueTypes() {
		return issueTypes;
	}

	public Boolean getCodeStored() {
		return codeStored;
	}

	public Boolean getUploadRemoved() {
		return uploadRemoved;
	}

	public Boolean getFullCodeCompared() {
		return fullCodeCompared;
	}

	public Boolean getHistoryInherited() {
		return historyInherited;
	}

	public Integer getAnalysisDepth() {
		return analysisDepth;
	}

	public Integer getMaxIssuesPerType() {
		return maxIssuesPerType;
	}

	public Php getPhp() {
		return php;
	}

	public Custom getCustom() {
		return custom;
	}
}
