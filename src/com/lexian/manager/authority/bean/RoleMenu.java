/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.authority.bean;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class RoleMenu {

	private int roleId;
	private int menuId;
	
	public RoleMenu() {
		// TODO Auto-generated constructor stub
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public RoleMenu(int roleId, int menuId) {
		super();
		this.roleId = roleId;
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RoleMenu [roleId=" + roleId + ", menuId=" + menuId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + menuId;
		result = prime * result + roleId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof RoleMenu){
			RoleMenu rm=(RoleMenu) obj;
			if(rm.getMenuId()==this.menuId&&rm.getRoleId()==this.getRoleId()){
				return true;
			}
		}
		
		
		return false;
	}
	
	
	
}
