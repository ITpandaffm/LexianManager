package com.lexian.manager.authority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.RoleManager;
import com.lexian.manager.authority.service.ManagerService;
import com.lexian.web.ResultHelper;

@Controller
@RequestMapping("handleManager")
public class HandleManagerController {
	@Autowired
	private ManagerService managerService;
	
	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService ManagerService) {
		this.managerService = ManagerService;
	}

	//handleManager/getManagers.do
	@ResponseBody
	@RequestMapping("getManagers.do")
	public ResultHelper getManagers(){
		return managerService.getManagers();
	}
	//handleManager/addManager.do?name=xxx
	//<insert id="insertAndGetId" useGeneratedKeys="true" keyProperty="userId" parameterType="com.chenzhou.mybatis.User">  
	@ResponseBody
	@RequestMapping("addManager.do")
	public ResultHelper addManager(Manager manager){
		return managerService.addManager(manager);
	}
	//handleManager/updateManager.do?id=82&name=1
	@ResponseBody
	@RequestMapping("updateManager.do")
	public ResultHelper updateManager(Manager manager){
		return managerService.updateManager(manager);
	}
	//handleManager/associateToRole.do?roleId=1&managerId=114
	@ResponseBody
	@RequestMapping("associateToRole.do")
	public ResultHelper associateToRole(RoleManager rm){
		return managerService.associateToRole(rm);
	}
	//handleManager/deleteManager.do?id=110
	@ResponseBody
	@RequestMapping("deleteManager.do")
	public ResultHelper deleteManager(int id){
		return managerService.deleteManagerById(id);
	}
	
}
