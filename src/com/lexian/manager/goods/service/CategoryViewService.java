package com.lexian.manager.goods.service;

import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

public interface CategoryViewService {

	public ResultHelper getAllCategoryView(Page page);
	
	public ResultHelper getFirstCategoryView(Page page);
	
	public ResultHelper getSecondCategoryView(Page page);
	
	public ResultHelper getThirdCategoryView(Page page);
}
