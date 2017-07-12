package com.lexian.manager.goods.dao;

import java.util.List;
import java.util.Map;

import com.lexian.manager.goods.bean.CategoryView;

public interface CategoryViewDao {

	public List<CategoryView> getAllCategoryView(Map<String, Object> params);
	
	public List<CategoryView> getFirstCategoryView(Map<String, Object> params);
	
	public List<CategoryView> getSecondCategoryView(Map<String, Object> params);
	
	public List<CategoryView> getThirdCategoryView(Map<String, Object> params);
	
	public int getCountCategory();
	
	public int getFirstCountCategory();
	
	public int getSecondCountCategory();
	
	public int getThirdCountCategory();
}
