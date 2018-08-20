package com.ripstech.apiconnector2.entity.receive.application;

import com.ripstech.apiconnector2.Phase;
import com.ripstech.apiconnector2.entity.receive.Application;
import com.ripstech.apiconnector2.entity.receive.User;
import com.ripstech.apiconnector2.entity.receive.application.scan.Php;
import com.ripstech.apiconnector2.entity.receive.application.scan.Process;
import com.ripstech.apiconnector2.entity.receive.application.scan.issue.Type;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

public class Scan {

	private long id;
	private Long loc;
	private Integer analysisDepth;
	private Boolean fullCodeCompared;
	private Upload upload;
	private Boolean uploadRemoved;
	private OffsetDateTime start;
	private Integer percent;
	private Object parent;
	private Object severityDistribution;
	private List<String> callbacks = Collections.emptyList();
	private Boolean extendedCallbacks;
	private User createdBy;
	private Process process;
	private Application application;
	private OffsetDateTime finish;
	private List<Type> issueTypes = Collections.emptyList();
	private Boolean codeStored;
	private List<String> fileExtensions = Collections.emptyList();
	private String version;
	private Php php;
	private List<TagItem> tags = Collections.emptyList();
	private Integer phase;
	private String sourceType;
	private String path;
	private Integer maxIssuesPerType;

	public long getId() {
		return this.id;
	}

	public Long getLoc() {
		return this.loc;
	}

	public Integer getAnalysisDepth() {
		return this.analysisDepth;
	}

	public Boolean getFullCodeCompared() {
		return this.fullCodeCompared;
	}

	public Upload getUpload() {
		return this.upload;
	}

	public Boolean getUploadRemoved() {
		return this.uploadRemoved;
	}

	public OffsetDateTime getStart() {
		return this.start;
	}

	public Integer getPercent() {
		return this.percent;
	}

	public Object getParent() {
		return this.parent;
	}

	public Object getSeverityDistribution() {
		return this.severityDistribution;
	}

	public List<String> getCallbacks() {
		return this.callbacks;
	}

	public Boolean getExtendedCallbacks() {
		return extendedCallbacks;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

	public Process getProcess() {
		return this.process;
	}

	public Application getApplication() {
		return this.application;
	}

	public OffsetDateTime getFinish() {
		return this.finish;
	}

	public List<Type> getIssueTypes() {
		return this.issueTypes;
	}

	public Boolean getCodeStored() {
		return this.codeStored;
	}

	public List<String> getFileExtensions() {
		return this.fileExtensions;
	}

	public String getVersion() {
		return this.version;
	}

	public Php getPhp() {
		return this.php;
	}

	public List<TagItem> getTags() {
		return this.tags;
	}

	public Integer getPhase() {
		return this.phase;
	}

	public Phase getPhaseName() {
		return Phase.getById(this.phase);
	}

	public String getSourceType() {
		return this.sourceType;
	}

	public String getPath() {
		return this.path;
	}

	public Integer getMaxIssuesPerType() {
		return maxIssuesPerType;
	}

	public static class TagItem {

		private int id;
		private String name;

		public int getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}

	}

}
