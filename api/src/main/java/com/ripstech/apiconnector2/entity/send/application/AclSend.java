package com.ripstech.apiconnector2.entity.send.application;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Optional;

@JsonRootName("acl")
public class AclSend {

	private Optional<Integer> ownerUser;
	private Optional<Integer> ownerTeam;
	private Optional<Boolean> view;
	private Optional<Boolean> delete;
	private Optional<Boolean> create;
	private Optional<Boolean> edit;
	private Optional<Boolean> scan;
	private Optional<Boolean> manage;

	public static AclSend createPost() {
		return new AclSend();
	}

	public static AclSend createPatch() {
		return new AclSend();
	}

	private AclSend() {}

	public AclSend setOwnerUser(Integer ownerUser) {
		this.ownerUser = Optional.ofNullable(ownerUser);
		return this;
	}

	public AclSend setOwnerTeam(Integer ownerTeam) {
		this.ownerTeam = Optional.ofNullable(ownerTeam);
		return this;
	}

	public AclSend setView(Boolean view) {
		this.view = Optional.ofNullable(view);
		return this;
	}

	public AclSend setDelete(Boolean delete) {
		this.delete = Optional.ofNullable(delete);
		return this;
	}

	public AclSend setCreate(Boolean create) {
		this.create = Optional.ofNullable(create);
		return this;
	}

	public AclSend setEdit(Boolean edit) {
		this.edit = Optional.ofNullable(edit);
		return this;
	}

	public AclSend setScan(Boolean scan) {
		this.scan = Optional.ofNullable(scan);
		return this;
	}

	public AclSend setManage(Boolean manage) {
		this.manage = Optional.ofNullable(manage);
		return this;
	}

	public Optional<Integer> getOwnerUser() {
		return this.ownerUser;
	}

	public Optional<Integer> getOwnerTeam() {
		return this.ownerTeam;
	}

	public Optional<Boolean> getView() {
		return this.view;
	}

	public Optional<Boolean> getDelete() {
		return this.delete;
	}

	public Optional<Boolean> getCreate() {
		return this.create;
	}

	public Optional<Boolean> getEdit() {
		return this.edit;
	}

	public Optional<Boolean> getScan() {
		return this.scan;
	}

	public Optional<Boolean> getManage() {
		return this.manage;
	}
}
