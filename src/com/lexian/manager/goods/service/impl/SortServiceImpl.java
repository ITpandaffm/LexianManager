package com.lexian.manager.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.goods.bean.Category;
import com.lexian.manager.goods.dao.SortDao;
import com.lexian.manager.goods.service.SortService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;

@Service
public class SortServiceImpl implements SortService{
	
	@Autowired
	private SortDao sortDao;
	

	public SortDao getSortDao() {
		return sortDao;
	}

	public void setSortDao(SortDao sortDao) {
		this.sortDao = sortDao;
	}

	@Override
	public ResultHelper getAllCategories() {
		List<Category> list = sortDao.getAllCategories();
		return new ResultHelper(Constant.code_success,list);
	}
	
	@Override
	public ResultHelper getCategoryByCategoryName(String categoryName) {

		Category category=sortDao.getCategoryByCategoryName(categoryName);
		
		return new ResultHelper(Constant.code_success,category);
	}

	@Override
	public ResultHelper updateCategoryById(int id, String categoryName) {
		
		sortDao.updateCategoryById(id, categoryName);
		return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper addCategory(Category category) {
		Category cate=sortDao.getCategory(category.getCategoryName(), category.getType(), category.getParentId());
		if (cate != null) {
			return new ResultHelper(Constant.code_entity_duplicated);
		}else{
		 sortDao.addCategory(category);
		 return new ResultHelper(Constant.code_success);
		}
	}

	@Override
	public ResultHelper deleteCategory(int id) {
		sortDao.deleteCategory(id);
	 return new ResultHelper(Constant.code_success);
	}

}
