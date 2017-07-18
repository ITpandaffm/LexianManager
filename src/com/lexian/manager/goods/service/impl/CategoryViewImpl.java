package com.lexian.manager.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.goods.bean.CategoryView;
import com.lexian.manager.goods.dao.CategoryViewDao;
import com.lexian.manager.goods.service.CategoryViewService;
import com.lexian.manager.plate.bean.Special;
import com.lexian.utils.Constant;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

@Service
public class CategoryViewImpl implements CategoryViewService{
	@Autowired
	private CategoryViewDao CategoryViewDao;

	public CategoryViewDao getCategoryViewDao() {
		return CategoryViewDao;
	}

	public void setCategoryViewDao(CategoryViewDao categoryViewDao) {
		CategoryViewDao = categoryViewDao;
	}

	@Override
	public ResultHelper getAllCategoryView(Integer pageNo) {

		Page page = new Page();
		page.setPageNo(pageNo);
		
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<CategoryView> orderssWithStore = CategoryViewDao.getAllCategoryViewPage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.code_success, page);

		return result;
	}

	@Override
	public ResultHelper getFirstCategoryView(Integer pageNo) {
		Page page = new Page();
		page.setPageNo(pageNo);
		
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<CategoryView> orderssWithStore = CategoryViewDao.getFirstCategoryViewPage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.code_success, page);

		return result;
	}

	@Override
	public ResultHelper getSecondCategoryView(Integer pageNo) {
		Page page = new Page();

		page.setPageNo(pageNo);
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<CategoryView> orderssWithStore = CategoryViewDao.getSecondCategoryViewPage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.code_success, page);

		return result;
	}

	@Override
	public ResultHelper getThirdCategoryView(Integer pageNo) {
		Page page = new Page();

	
		page.setPageNo(pageNo);	
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<CategoryView> orderssWithStore = CategoryViewDao.getThirdCategoryViewPage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.code_success, page);

		return result;
	}

}
