package com.lexian.manager.authority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.RoleManager;
import com.lexian.manager.authority.service.HandleManagerService;
import com.lexian.web.ResultHelper;

@Controller
@RequestMapping("handleManager")
public class HandleManagerController {
	@Autowired
	private HandleManagerService handleManagerService;
	
	public HandleManagerService getHandleManagerService() {
		return handleManagerService;
	}

	public void setHandleManagerService(HandleManagerService handleManagerService) {
		this.handleManagerService = handleManagerService;
	}

	//handleManager/getManagers.do
	@ResponseBody
	@RequestMapping("getManagers.do")
	public ResultHelper getManagers(){
		return handleManagerService.getManagers();
	}
	
	@ResponseBody
	@RequestMapping("addManager.do")
	public ResultHelper addManager(Manager manager){
		return handleManagerService.addManager(manager);
	}
	@ResponseBody
	@RequestMapping("updateManagerInfo.do")
	public ResultHelper updateManagerInfo(Manager manager){
		return handleManagerService.updateManagerInfo(manager);
	}
	@ResponseBody
	@RequestMapping("associateToRole.do")
	public ResultHelper associateToRole(RoleManager rm){
		return handleManagerService.associateToRole(rm);
	}
}
