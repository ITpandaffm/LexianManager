/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.goods.bean.CategoryView;
import com.lexian.manager.goods.dao.CategoryViewDao;
import com.lexian.manager.goods.service.CategoryViewService;
import com.lexian.utils.Constant;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
@Service
public class CategoryViewImpl implements CategoryViewService{
	@Autowired
	private CategoryViewDao categoryViewDao;

	public CategoryViewDao getCategoryViewDao() {
		return categoryViewDao;
	}

	public void setCategoryViewDao(CategoryViewDao categoryViewDao) {
		this.categoryViewDao = categoryViewDao;
	}

	@Override
	public ResultHelper getAllCategoryView(Page page) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<CategoryView> orderssWithStore = categoryViewDao.getAllCategoryViewPage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);

		return result;
	}

	@Override
	public ResultHelper getFirstCategoryView(Page page) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<CategoryView> orderssWithStore = categoryViewDao.getFirstCategoryViewPage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);

		return result;
	}

	@Override
	public ResultHelper getSecondCategoryView(Page page) {

		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<CategoryView> orderssWithStore = categoryViewDao.getSecondCategoryViewPage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);

		return result;
	}

	@Override
	public ResultHelper getThirdCategoryView(Page page) {
	
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<CategoryView> orderssWithStore = categoryViewDao.getThirdCategoryViewPage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);

		return result;
	}

}
