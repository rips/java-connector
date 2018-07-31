package com.ripstech.apiconnector2.entity.receive;

import java.util.Collections;
import java.util.List;

public class Status {

	private User user;
	private Users users;
	private String version;
	private Boolean cloud;
	private Boolean maintenance;
	private String hardwareId;
	private Census census;
	private List<String> trialIssueTypes = Collections.emptyList();
	private List<String> fileExtensions = Collections.emptyList();

	public User getUser() {
		return this.user;
	}

	public Users getUsers() {
		return this.users;
	}

	public String getVersion() {
		return this.version;
	}

	public Boolean getCloud() {
		return this.cloud;
	}

	public Boolean getMaintenance() {
		return this.maintenance;
	}

	public String getHardwareId() {
		return this.hardwareId;
	}

	public Census getCensus() {
		return this.census;
	}

	public List<String> getTrialIssueTypes() {
		return this.trialIssueTypes;
	}

	public List<String> getFileExtensions() {
		return this.fileExtensions;
	}

	public class Users {

		private Integer max;
		private Integer enabled;

		public Integer getMax() {
			return this.max;
		}

		public Integer getEnabled() {
			return this.enabled;
		}

	}

	public static class Census {

		private Integer scans;
		private Integer issues;
		private Integer loc;

		public Integer getScans() {
			return this.scans;
		}

		public Integer getIssues() {
			return this.issues;
		}

		public Integer getLoc() {
			return this.loc;
		}

	}
}
