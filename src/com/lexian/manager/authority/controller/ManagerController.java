package com.lexian.manager.authority.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.service.ManagerService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;

@Controller
@SessionAttributes(value={"managerId","privilegeUrls"},types={Integer.class,List.class})
@RequestMapping("manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	public ManagerService getManagerService() {
		return managerService;
	}
	
	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
	
	/**
	 * 登录
	 * @param name
	 * @param password
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("signIn.do")
	public ResultHelper signIn(String name, String password, HttpSession session,Map<String, Object> model) {

		ResultHelper result = managerService.signIn(name, password);

		if (result.getCode() == Constant.code_success) {
			Manager manager = (Manager) result.getData();
			session.setAttribute("managerId", manager.getId());
			session.setAttribute("privilegeUrls", managerService.getPrivilegeUrls(manager.getId()).getData());
			model.put("managerId", manager.getId());
			
		}
		
		
		return result;
	}
//	public ResultHelper signIn(String name, String password,Map<String, Object> model) {
//
//		ResultHelper result = managerService.signIn(name, password);
//		if (result.getCode() == Constant.code_success) {
//			Manager manager = (Manager) result.getData();
//			//session.setAttribute("privilegeUrls", managerService.getPrivilegeUrls(manager.getId()).getData());
//			model.put("managerId", manager.getId());
//			model.put("privilegeUrls",managerService.getPrivilegeUrls(manager.getId()).getData());
//		}
//		return result;
//	}
	/**
	 * 退出登录
	 * @param values
	 * @return
	 */
	@ResponseBody
	@RequestMapping("signOut.do")
	public ResultHelper signOut(SessionStatus status) {
		status.setComplete();
		return new ResultHelper(Constant.code_success);
	}
	
	/**
	 * 修改密码
	 * @param managerId
	 * @param newPassword
	 * @return
	 */
	// manager/updateManager.do?password=1
	@ResponseBody
	@RequestMapping("updateManager.do")
	public ResultHelper updateManager(Manager manager,Map<String, Object> model) {
		manager.setId((Integer) model.get("managerId"));
		return managerService.updateManager(manager);
	}
	
	/**
	 * 获取权限列表
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getPrivileges.do")
	public ResultHelper getPrivileges(Map<String, Object> model) {

		return managerService.getPrivileges((Integer) model.get("managerId"));
	}
	
	/**
	 * 获取菜单列表
	 * @param model
	 * @return
	 */
	//manager/getMenus.do
	@ResponseBody
	@RequestMapping("getMenus.do")
	public ResultHelper getMenus(Map<String, Object> model) {
		ResultHelper result=managerService.getMenus((Integer) model.get("managerId"));
		return result;
	}

}
