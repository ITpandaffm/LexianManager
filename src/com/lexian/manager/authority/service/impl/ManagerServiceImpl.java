package com.lexian.manager.authority.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.Privilege;
import com.lexian.manager.authority.bean.RoleManager;
import com.lexian.manager.authority.dao.ManagerDao;
import com.lexian.manager.authority.dao.RoleManagerDao;
import com.lexian.manager.authority.service.ManagerService;
import com.lexian.utils.Constant;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;
@Service
public class ManagerServiceImpl implements ManagerService{
	
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
	public ResultHelper signIn(String name, String password) {
		
		Manager manager=managerDao.getManagerByNameAndPassword(name, password);
		
		ResultHelper result;
		if(manager!=null){
			result=new ResultHelper(Constant.code_success, manager);
		}else{
			result=new ResultHelper(Constant.code_login_failed);
		}
		
		return result;
	}
	@Override
	public ResultHelper getPrivileges(Integer id,Integer pageNo) {
		Page page = new Page();

		if (pageNo != null) {
			page.setPageNo(pageNo);
		}

		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		params.put("id", id);
		List<Privilege> privileges= managerDao.getPrivilegesPage(params);
		page.setData(privileges);
	

		ResultHelper result = new ResultHelper(Constant.code_success, page);

		return result;
	}

	@Override
	public ResultHelper getUserWithMenus(Integer id) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.code_success,managerDao.getUserWithMenus(id));
	}

	@Override
	public ResultHelper getPrivilegeUrls(Integer id) {
		
		return new ResultHelper(Constant.code_success,managerDao.getPrivilegeUrls(id));
	}
	
	
	@Override
	public ResultHelper addManager(Manager manager) {
		Date time=new Date();
		manager.setCreateTime(time);
		manager.setUpdateTime(time);
		managerDao.addManager(manager);
		return new ResultHelper(Constant.code_success,manager);
	}

	@Override
	public ResultHelper updateManager(Manager manager) {
		Date time=new Date();
		manager.setUpdateTime(time);
		managerDao.updateManager(manager);
		return new ResultHelper(Constant.code_success);
	}
	
	
	@Override
	public ResultHelper updateManagerPassword(Manager manager,String newPass) {
		
		ResultHelper result=verifyPassword(manager.getId(), manager.getPassword());
		if(result.getCode()==Constant.code_success){
			manager.setPassword(newPass);
			updateManager(manager);
		}
		return result;
		
		
	}

	@Override
	public ResultHelper associateToRole(RoleManager rm) {
		rolePrivilegeDao.insertRoleManager(rm);
		return new ResultHelper(Constant.code_success);
	}


	@Override
	public ResultHelper getManagers(Integer pageNo) {
		
		Page page = new Page();

		if (pageNo != null) {
			page.setPageNo(pageNo);
		}

		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<Manager> privileges= managerDao.getManagersPage(params);
		page.setData(privileges);
	

		ResultHelper result = new ResultHelper(Constant.code_success, page);

		return result;
		

	}

	@Override
	public ResultHelper deleteManagerById(Integer id) {
		managerDao.deleteManagerById(id);
		return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper verifyPassword(Integer id, String password) {
		
		Integer manager=managerDao.verifyPassword(id,password);
		
		ResultHelper result;
		if(manager==null){
			result=new ResultHelper(Constant.code_login_failed);
		}else{
			
			result=new ResultHelper(Constant.code_success);
		}
		
		return result;
		
	}
	
}
