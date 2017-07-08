package com.lexian.manager.authority.service;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.RoleManager;
import com.lexian.web.ResultHelper;

public interface HandleManagerService {
	
	public ResultHelper addManager(Manager manager);
	
	public ResultHelper updateManagerInfo(Manager manager);
	
	public ResultHelper associateToRole(RoleManager rl);

	public ResultHelper getManagers();
}
