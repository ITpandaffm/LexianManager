package com.lexian.manager.goods.service;

import com.lexian.web.ResultHelper;

public interface CategoryViewService {

	public ResultHelper getAllCategoryView(Integer pageNo);
	
	public ResultHelper getFirstCategoryView(Integer pageNo);
	
	public ResultHelper getSecondCategoryView(Integer pageNo);
	
	public ResultHelper getThirdCategoryView(Integer pageNo);
}
