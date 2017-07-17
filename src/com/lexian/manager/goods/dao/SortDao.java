package com.lexian.manager.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.goods.bean.Category;

public interface SortDao {

	public List<Category> getAllCategories();
	
	public Category getCategoryByCategoryName(String categoryName);
	
	public void updateCategoryById(@Param("id")int id,@Param("categoryName")String categoryName);
	
	public void addCategory(Category category);
	
	public void deleteCategory(int id);
	
	public Category getCategory(@Param("categoryName")String categoryName,@Param("type")int type,@Param("parentId")Integer parentId);
	
}
