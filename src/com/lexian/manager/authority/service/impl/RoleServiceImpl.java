package com.lexian.manager.authority.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.authority.bean.Role;
import com.lexian.manager.authority.dao.RoleDao;
import com.lexian.manager.authority.service.RoleService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;
import com.sun.jmx.snmp.Timestamp;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

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
		
		role.setCreateTime(new Date());
		
		
		return new ResultHelper(Constant.code_success,roleDao.addRole(role));
	}

	@Override
	public ResultHelper updateRole(Role role) {
		
		role.setUpdateTime(new Date());
		roleDao.updateRole(role);
		
		return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper createMenusAndPrivileges() {
		
		
		
		return null;
	}
	
	
	
}
