package com.lexian.manager.authority.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.Privilege;
import com.lexian.manager.authority.service.ManagerService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

@Controller
@SessionAttributes(value={"managerId"},types={Integer.class})
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
	public ResultHelper signIn(String name, String password, HttpSession session) {

		ResultHelper result = managerService.signIn(name, password);

		if (result.getCode() == Constant.code_success) {
			Manager manager = (Manager) result.getData();
			session.setAttribute("managerId", manager.getId());
			session.setAttribute("privileges", managerService.getPrivileges(manager.getId()).getData());;
		}
		// manager/signIn.do?name=13800138000&password=123456
		// manager/signIn.do?name=13800138000&password=123456
		return result;
	}
	
	/**
	 * 退出登录
	 * @param values
	 * @return
	 */
	@ResponseBody
	@RequestMapping("signOut.do")
	public ResultHelper signOut(HttpSession session) {
		session.invalidate();
		return new ResultHelper(Constant.code_success);
	}
	
	/**
	 * 修改密码
	 * @param managerId
	 * @param newPassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updatePassword.do")
	public ResultHelper updatePassword(@RequestParam("managerId") int managerId, String newPassword) {

		return managerService.updatePasswordById(managerId, newPassword);
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
	@ResponseBody
	@RequestMapping("getMenus.do")
	public ResultHelper getMenus(Map<String, Object> model) {

		return managerService.getMenus((Integer) model.get("managerId"));
	}

}
