package com.lexian.manager.authority.bean;

public class RolePrivilege {
	private int id;
	private int roleId;
	private int privilegeId;
	
	
	public RolePrivilege() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RolePrivilege(int roleId, int privilegeId) {
		super();
		this.roleId = roleId;
		this.privilegeId = privilegeId;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + privilegeId;
		result = prime * result + roleId;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof RolePrivilege){
			RolePrivilege rp=(RolePrivilege) obj;
			if(this.getPrivilegeId()==rp.getPrivilegeId()&&this.getRoleId()==rp.getRoleId()){
				return true;
			}
		}
		
		return false;
	}


	@Override
	public String toString() {
		return "RolePrivilege [roleId=" + roleId + ", privilegeId=" + privilegeId + "]";
	}


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
