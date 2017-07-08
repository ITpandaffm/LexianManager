package com.lexian.manager.authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.RoleManager;
import com.lexian.manager.authority.dao.ManagerDao;
import com.lexian.manager.authority.dao.RoleDao;
import com.lexian.manager.authority.dao.RoleManagerDao;
import com.lexian.manager.authority.service.HandleManagerService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;
@Service
public class HandleManagerServiceImpl implements HandleManagerService{
	

	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private RoleManagerDao rolePrivilegeDao;
	
	
	
	
	public ManagerDao getManagerDao() {
		return managerDao;
	}

	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}

	public RoleManagerDao getRolePrivilegeDao() {
		return rolePrivilegeDao;
	}

	public void setRolePrivilegeDao(RoleManagerDao rolePrivilegeDao) {
		this.rolePrivilegeDao = rolePrivilegeDao;
	}

	@Override
	public ResultHelper addManager(Manager manager) {
		
		return new ResultHelper(Constant.code_success, managerDao.addManager(manager));
	}

	@Override
	public ResultHelper updateManagerInfo(Manager manager) {
		
		return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper associateToRole(RoleManager rm) {
		rolePrivilegeDao.insertRoleManager(rm);
		return new ResultHelper(Constant.code_success);
	}


	@Override
	public ResultHelper getManagers() {

		ResultHelper result=new ResultHelper(Constant.code_success,managerDao.getManagers());
		return result;
	}

}
