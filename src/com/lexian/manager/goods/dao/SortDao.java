package com.lexian.manager.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.goods.bean.Category;

public interface SortDao {

	public List<Category> getAllCategories();
	
	public Category getCategoryByCategoryName(String categoryName);
	
	public void updateCategoryById(@Param("id")int id,@Param("categoryname")String categoryName);
	
	public void addCategory(Category category);
	
	public void deleteCategory(int id);
	
}
