/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.goods.bean.Category;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public interface SortDao {

	public List<Category> getAllCategoriesPage(Map<String, Object> params);
	
	public Category getCategoryByCategoryName(String categoryName);
	
	public void updateCategoryById(@Param("id")int id,@Param("categoryName")String categoryName);
	
	public void addCategory(Category category);
	
	public void deleteCategory(int id);
	
	public Category getCategory(@Param("categoryName")String categoryName,@Param("type")int type,
			@Param("parentId")Integer parentId);
	
	public Category getCountCategory(@Param("categoryName")String categoryName,@Param("type")int type);
	
	public int getCountCategoryByParentId(int parentId);

	public List<Category> getCategories();
}
