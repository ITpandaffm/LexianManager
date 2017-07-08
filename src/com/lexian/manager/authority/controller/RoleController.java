package com.lexian.manager.authority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lexian.manager.authority.bean.Role;
import com.lexian.manager.authority.service.RoleService;
import com.lexian.web.ResultHelper;

@Controller
@RequestMapping("role")
@SessionAttributes(value={"managerId"},types={Integer.class})
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	@ResponseBody
	@RequestMapping("getRoles.do")
	public ResultHelper getRoles(){
		return roleService.getRoles();
	}
	@ResponseBody
	@RequestMapping("addRole.do")
	public ResultHelper addRole(Role role){
		return roleService.addRole(role);
	}
	@ResponseBody
	@RequestMapping("updateRole.do")
	public ResultHelper updateRole(Role role){
		return roleService.updateRole(role);
	}
	
	
	
	@ResponseBody
	@RequestMapping("createMenusAndPrivileges.do")
	public ResultHelper createMenusAndPrivileges(){
		return roleService.createMenusAndPrivileges();
	}
}
