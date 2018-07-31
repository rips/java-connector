package com.ripstech.apiconnector2.entity.receive.application;

import com.ripstech.apiconnector2.entity.receive.Application;
import com.ripstech.apiconnector2.entity.receive.Team;
import com.ripstech.apiconnector2.entity.receive.User;

public class Acl {

	private int id;
	private Boolean immutable;
	private Boolean manage;
	private Boolean view;
	private Team ownerTeam;
	private Boolean edit;
	private Boolean delete;
	private Application application;
	private User ownerUser;
	private Boolean create;
	private Boolean scan;
	private User createdBy;

	public int getId() {
		return this.id;
	}

	public Boolean getImmutable() {
		return this.immutable;
	}

	public Boolean getManage() {
		return this.manage;
	}

	public Boolean getView() {
		return this.view;
	}

	public Team getOwnerTeam() {
		return this.ownerTeam;
	}

	public Boolean getEdit() {
		return this.edit;
	}

	public Boolean getDelete() {
		return this.delete;
	}

	public Application getApplication() {
		return this.application;
	}

	public User getOwnerUser() {
		return this.ownerUser;
	}

	public Boolean getCreate() {
		return this.create;
	}

	public Boolean getScan() {
		return this.scan;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

}
