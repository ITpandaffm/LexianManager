/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.authority.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.Menu;
import com.lexian.manager.authority.bean.Privilege;
import com.lexian.manager.authority.bean.RoleManager;
import com.lexian.manager.authority.dao.ManagerDao;
import com.lexian.manager.authority.dao.RoleManagerDao;
import com.lexian.manager.authority.service.ManagerService;
import com.lexian.utils.Constant;
import com.lexian.utils.UrlContant;
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
@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDao managerDao;

	@Autowired
	private RoleManagerDao roleManagerDao;

	public ManagerDao getManagerDao() {
		return managerDao;
	}

	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}


	@Override
	public ResultHelper signIn(String name, String password) {

		Manager manager = managerDao.getManagerByNameAndPassword(name, password);

		ResultHelper result;
		if (manager != null) {
			
			if(manager.getStatus()!=1){
				result=new ResultHelper(Constant.CODE_STATE_FORBID, manager);
			}else{
				result = new ResultHelper(Constant.CODE_SUCCESS, manager);
			}
		} else {
			result = new ResultHelper(Constant.CODE_LOGIN_FAILED);
		}

		return result;
	}

	@Override
	public ResultHelper getPrivileges(Integer id, Page page) {

		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		params.put("id", id);
		List<Privilege> privileges = managerDao.getPrivilegesPage(params);
		page.setData(privileges);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);

		return result;
	}

	@Override
	public ResultHelper getUserWithMenus(Integer id) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.CODE_SUCCESS, managerDao.getUserWithMenus(id));
	}

	@Override
	public ResultHelper getPrivilegeUrls(Integer id) {

		return new ResultHelper(Constant.CODE_SUCCESS, managerDao.getPrivilegeUrls(id));
	}

	@Override
	public ResultHelper addManager(Manager manager,Integer[] roleId) {
		

		ResultHelper result;
		if(managerDao.hasNameUsed(manager.getName())==0){
			Date time=new Date();
			manager.setCreateTime(time);
			
			manager.setUpdateTime(time);
			if(manager.getStatus()==null){
				manager.setStatus(1);
			}
			
			managerDao.addManager(manager);
			
			if(roleId!=null){
				List<RoleManager> rms=new ArrayList<>();
				for(Integer rid:roleId){
					RoleManager rm=new RoleManager();
					rm.setManagerId(manager.getId());
					rm.setRoleId(rid);
					rms.add(rm);
				}
				
				roleManagerDao.insertRoleManagerBatch(rms);
			}
			
			
			result=new ResultHelper(Constant.CODE_SUCCESS);
		}else{
			result=new ResultHelper(Constant.CODE_INVALID_PARAMETER);
		}
		return result;
	}

	@Override
	public ResultHelper updateManager(Manager manager) {
		Date time = new Date();
		manager.setUpdateTime(time);
		managerDao.updateManager(manager);
		return new ResultHelper(Constant.CODE_SUCCESS);
	}

	@Override
	public ResultHelper updateManagerPassword(Manager manager, String newPass) {

		ResultHelper result = verifyPassword(manager.getId(), manager.getPassword());
		if (result.getCode() == Constant.CODE_SUCCESS) {
			manager.setPassword(newPass);
			updateManager(manager);
		}
		return result;

	}

	@Override
	public ResultHelper getManagers(Page page) {

		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<Manager> privileges = managerDao.getManagersPage(params);
		page.setData(privileges);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);

		return result;

	}

	@Override
	public ResultHelper deleteManagerById(Integer id) {
		managerDao.deleteManagerById(id);
		return new ResultHelper(Constant.CODE_SUCCESS);
	}

	@Override
	public ResultHelper verifyPassword(Integer id, String password) {

		Integer manager = managerDao.verifyPassword(id, password);

		ResultHelper result;
		if (manager == null) {
			result = new ResultHelper(Constant.CODE_LOGIN_FAILED);
		} else {

			result = new ResultHelper(Constant.CODE_SUCCESS);
		}
		return result;

	}

	@Override
	public ResultHelper getMenus(Integer id, Page page) {

		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		params.put("id", id);
		List<Menu> menus = managerDao.getMenusPage(params);
		page.setData(menus);
		
		for(Menu menu:menus){
			menu.setBackUrl(UrlContant.qiNiuUrl+"/"+menu.getBackUrl());
		}
		
		return new ResultHelper(Constant.CODE_SUCCESS,page);
	}

	@Override
	public ResultHelper updateAssociatedRole(Manager manager,Integer[] newRoleId) {
		
		
		Date time=new Date();
		manager.setCreateTime(time);
		manager.setUpdateTime(time);
		managerDao.updateManager(manager);
		roleManagerDao.deleteRoleManagerByManagerId(manager.getId());
		List<RoleManager> rms=new ArrayList<>();
		for(Integer rid:newRoleId){
			RoleManager rm=new RoleManager();
			rm.setManagerId(manager.getId());
			rm.setRoleId(rid);
			rms.add(rm);
		}
		roleManagerDao.insertRoleManagerBatch(rms);
		
		return new ResultHelper(Constant.CODE_SUCCESS);
	}


}
