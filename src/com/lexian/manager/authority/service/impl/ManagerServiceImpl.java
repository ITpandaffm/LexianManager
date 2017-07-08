package com.lexian.manager.authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.dao.ManagerDao;
import com.lexian.manager.authority.service.ManagerService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;
@Service
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	private ManagerDao managerDao;
	
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
	public ResultHelper updatePasswordById(int id, String newPassword) {
		
		managerDao.updatePasswordById(id, newPassword);
		
		return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper getPrivileges(int id) {
		
		return new ResultHelper(Constant.code_success,managerDao.getPrivileges(id));
	}

	@Override
	public ResultHelper getMenus(int id) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.code_success,managerDao.getMenus(id));
	}

}
