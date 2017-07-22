package com.lexian.manager.goods.dao;

import java.util.List;
import java.util.Map;

import com.lexian.manager.goods.bean.CategoryView;

public interface CategoryViewDao {

	public List<CategoryView> getAllCategoryViewPage(Map<String, Object> params);
	
	public List<CategoryView> getFirstCategoryViewPage(Map<String, Object> params);
	
	public List<CategoryView> getSecondCategoryViewPage(Map<String, Object> params);
	
	public List<CategoryView> getThirdCategoryViewPage(Map<String, Object> params);
	
	/*public int getCountCategory();
	
	public int getFirstCountCategory();
	
	public int getSecondCountCategory();
	
	public int getThirdCountCategory();*/
}
