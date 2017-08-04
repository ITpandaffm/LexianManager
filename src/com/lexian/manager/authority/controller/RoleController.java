/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.authority.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.manager.authority.bean.Role;
import com.lexian.manager.authority.service.RoleService;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	// role/getRoles.do
	@ResponseBody
	@RequestMapping("getRoles.do")
	public ResultHelper getRoles(Page page) {
		return roleService.getRoles(page);
	}

	// role/getRoles.do
	@ResponseBody
	@RequestMapping("getAllRoles.do")
	public ResultHelper getAllRoles() {
		return roleService.getAllRoles();
	}

	// role/addRole.do?name=supermanager
	@ResponseBody
	@RequestMapping("addRole.do")
	public ResultHelper addRole(@Valid Role role) {
		return roleService.addRole(role);
	}

	// role/updateRole.do?id=4&&name=wwww
	@ResponseBody
	@RequestMapping("updateRole.do")
	public ResultHelper updateRole(@Valid Role role) {

		return roleService.updateRole(role);
	}

	// role/getMenus.do?id=3
	@ResponseBody
	@RequestMapping("getMenus.do")
	public ResultHelper getMenus(Integer id) {
		return roleService.getMenus(id);
	}

	// role/getPrivileges.do?id=3
	@ResponseBody
	@RequestMapping("getPrivileges.do")
	public ResultHelper getPrivileges(Integer id) {
		return roleService.getPrivileges(id);
	}

	// role/updateMenus.do?id=5&menuId=31&menuId=32&menuId=45&menuId=28
	@ResponseBody
	@RequestMapping("updateMenus.do")
	public ResultHelper updateMenus(int[] menuId, Integer id) {
		return roleService.updateMenus(id, menuId);
	}

	// role/updatePrivileges.do?id=5&privilegeId=429&privilegeId=435&privilegeId=432&privilegeId=445
	@ResponseBody
	@RequestMapping("updatePrivileges.do")
	public ResultHelper updatePrivileges(int[] privilegeId, Integer id) {
		return roleService.updatePrivileges(id, privilegeId);
	}

}
