package com.ripstech.apiconnector2.entity.receive.quota;

import com.ripstech.apiconnector2.entity.receive.Quota;
import com.ripstech.apiconnector2.entity.receive.Team;
import com.ripstech.apiconnector2.entity.receive.User;

public class Acl {

	private long id;
	private Boolean immutable;
	private Boolean manage;
	private Boolean view;
	private Boolean use;
	private Team ownerTeam;
	private Boolean edit;
	private Boolean delete;
	private User ownerUser;
	private Quota quota;
	private Boolean create;
	private User createdBy;

	public long getId() {
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

	public Boolean getUse() {
		return this.use;
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

	public User getOwnerUser() {
		return this.ownerUser;
	}

	public Quota getQuota() {
		return this.quota;
	}

	public Boolean getCreate() {
		return this.create;
	}

	public User getCreatedBy() {
		return createdBy;
	}

}
