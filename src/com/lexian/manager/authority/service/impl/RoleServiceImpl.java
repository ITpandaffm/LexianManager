package com.lexian.manager.authority.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.authority.bean.Role;
import com.lexian.manager.authority.bean.RoleMenu;
import com.lexian.manager.authority.bean.RolePrivilege;
import com.lexian.manager.authority.dao.RoleDao;
import com.lexian.manager.authority.dao.RoleMenuDao;
import com.lexian.manager.authority.dao.RolePrivilegeDao;
import com.lexian.manager.authority.service.RoleService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleMenuDao roleMenuDao;
	
	@Autowired
	private RolePrivilegeDao rolePrivilegeDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public ResultHelper getRoles() {
		return new ResultHelper(Constant.code_success, roleDao.getRoles());
	}

	@Override
	public ResultHelper addRole(Role role) {
		
		Date time=new Date();
		role.setCreateTime(time);
		
		role.setUpdateTime(time);
		
		roleDao.addRole(role);
		
		return new ResultHelper(Constant.code_success,role);
	}

	@Override
	public ResultHelper updateRole(Role role) {
		
		role.setUpdateTime(new Date());
		roleDao.updateRole(role);
		
		return new ResultHelper(Constant.code_success);
	}

	

	@Override
	public ResultHelper getMenus(Integer id) {
		
		Object[] data=new Object[2];
		
		data[0]=roleDao.getMenus();
		
		data[1]=roleDao.getMenusById(id);
		
		
		return new ResultHelper(Constant.code_success,data);
	}

	@Override
	public ResultHelper updateMenus(Integer id, int[] menuIds) {
		
		List<RoleMenu> rms=roleMenuDao.getRoleMenus(id);
		
		List<RoleMenu> newRms=new LinkedList<>();
		List<RoleMenu> newRms2=new LinkedList<>();
		for(int menuId:menuIds){
			RoleMenu rm=new RoleMenu(id,menuId);
			newRms.add(rm);
			newRms2.add(rm);
		}			
		//只添加新增的
		newRms.removeAll(rms);
		if(newRms.size()>0){
			roleMenuDao.insertByBatch(newRms);
		}
		//现在没有的要删除
		rms.removeAll(newRms2);
		if(rms.size()>0){
			roleMenuDao.deleteByBatch(id,rms);
		}
		
		
		return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper updatePrivileges(Integer id, int[] privilegeIds) {
		List<RolePrivilege> rps=rolePrivilegeDao.getRolePrivileges(id);
		
		List<RolePrivilege> newRps=new LinkedList<>();
		List<RolePrivilege> newRps2=new LinkedList<>();
		for(int privilegeId:privilegeIds){
			RolePrivilege rp=new RolePrivilege(id,privilegeId);
			newRps.add(rp);
			newRps2.add(rp);
		}			
		//只添加新增的
		newRps.removeAll(rps);
		if(newRps.size()>0){
			rolePrivilegeDao.insertByBatch(newRps);
		}
		//现在没有的要删除
		rps.removeAll(newRps2);
		if(rps.size()>0){
			rolePrivilegeDao.deleteByBatch(id,rps);
		}
		
		
		return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper getPrivileges(int id) {
		Object[] data=new Object[2];
		
		data[0]=roleDao.getPrivileges();
		
		data[1]=roleDao.getPrivilegesById(id);
		
		return new ResultHelper(Constant.code_success,data);
	}

	
	
	
	
}
