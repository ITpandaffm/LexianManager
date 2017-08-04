/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.goods.dao;

import java.util.List;
import java.util.Map;

import com.lexian.manager.goods.bean.CategoryView;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
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
