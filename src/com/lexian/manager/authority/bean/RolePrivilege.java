package com.lexian.manager.authority.bean;

public class RolePrivilege {
	private int id;
	private int roleId;
	private int privilegeId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

}
