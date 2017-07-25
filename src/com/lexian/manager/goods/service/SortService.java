package com.lexian.manager.goods.service;

import com.lexian.manager.goods.bean.Category;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

public interface SortService {
	public ResultHelper getCategories();

	public ResultHelper getAllCategories(Page page);
	
	public ResultHelper getCategoryByCategoryName(String categoryName);
	
	public ResultHelper updateCategoryById(int id,String categoryName);
	
	public ResultHelper addCategory(Category category);
	
	public ResultHelper deleteCategory(int id);
}
