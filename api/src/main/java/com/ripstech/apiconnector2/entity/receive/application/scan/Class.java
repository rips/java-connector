package com.ripstech.apiconnector2.entity.receive.application.scan;

import com.ripstech.apiconnector2.entity.receive.Application;
import com.ripstech.apiconnector2.entity.receive.User;
import com.ripstech.apiconnector2.entity.receive.application.Upload;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.Type;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Class {

	private int id;
	private String version;
	private OffsetDateTime start;
	private OffsetDateTime finish;
	private Integer phase;
	private Integer percent;
	private Integer loc;
	private Boolean codeStored;
	private Boolean uploadRemoved;
	private String sourceType;
	private Php php;
	private Process process;
	private Upload upload;
	private Application application;
	private User createdBy;
	private List<Type> issueTypes = Collections.emptyList();
	private Class parent;
	private Map<String, Integer> severityDistributions = Collections.emptyMap();
	private Integer analysisDepth;
	private List<String> callbacks = Collections.emptyList();
	private List<String> tags = Collections.emptyList();
	private List<String> fileExtensions = Collections.emptyList();
	private Integer startLine;
	private Integer endLine;
	private String name;

	public int getId() {
		return this.id;
	}

	public String getVersion() {
		return this.version;
	}

	public OffsetDateTime getStart() {
		return this.start;
	}

	public OffsetDateTime getFinish() {
		return this.finish;
	}

	public Integer getPhase() {
		return this.phase;
	}

	public Integer getPercent() {
		return this.percent;
	}

	public Integer getLoc() {
		return this.loc;
	}

	public Boolean getCodeStored() {
		return this.codeStored;
	}

	public Boolean getUploadRemoved() {
		return this.uploadRemoved;
	}

	public String getSourceType() {
		return this.sourceType;
	}

	public Php getPhp() {
		return this.php;
	}

	public Process getProcess() {
		return this.process;
	}

	public Upload getUpload() {
		return this.upload;
	}

	public Application getApplication() {
		return this.application;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

	public List<Type> getIssueTypes() {
		return this.issueTypes;
	}

	public Class getParent() {
		return this.parent;
	}

	public Map<String, Integer> getSeverityDistributions() {
		return this.severityDistributions;
	}

	public Integer getAnalysisDepth() {
		return this.analysisDepth;
	}

	public List<String> getCallbacks() {
		return this.callbacks;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public List<String> getFileExtensions() {
		return this.fileExtensions;
	}

	public Integer getStartLine() {
		return this.startLine;
	}

	public Integer getEndLine() {
		return this.endLine;
	}

	public String getName() {
		return this.name;
	}

}
