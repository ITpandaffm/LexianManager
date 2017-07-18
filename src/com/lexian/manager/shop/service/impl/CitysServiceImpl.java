package com.lexian.manager.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.shop.dao.CitysDao;
import com.lexian.manager.shop.service.CitysService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;
@Service
public class CitysServiceImpl implements CitysService {

	@Autowired
	private CitysDao citysDao;
	
	@Override
	public ResultHelper getCities(Integer parentId) {
		// TODO Auto-generated method stub
		ResultHelper result=new ResultHelper(Constant.code_success,citysDao.getCitiesByParentId(parentId));	
		return result;
	}

}
