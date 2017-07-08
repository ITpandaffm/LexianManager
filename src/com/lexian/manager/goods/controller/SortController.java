package com.lexian.manager.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.manager.goods.bean.Category;
import com.lexian.manager.goods.service.SortService;
import com.lexian.web.ResultHelper;

@Controller
@RequestMapping("sort")
//@SessionAttributes(value={"managerId"},types={Integer.class})
public class SortController {
	@Autowired
	private SortService sortService;
	
	public SortService getSortService() {
		return sortService;
	}

	public void setSortService(SortService sortService) {
		this.sortService = sortService;
	}

	@ResponseBody
	@RequestMapping("getAllCategories.do")
	 public void getAllCategories(){
		 
	 }
	/**
	 * 
	 * @param categoryName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCategoryByCategoryName.do")
	 public ResultHelper getCategoryByCategoryName(String categoryName){
		ResultHelper result=sortService.getCategoryByCategoryName(categoryName);
		//sort/getCategoryByCategoryName.do?categoryName=学习装备
		return result;
		
	 }
	/**
	 * 
	 * @param category
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addCategory.do")
	 public ResultHelper addCategory(Category category){
		//
		return sortService.addCategory(category);
		 
	 }
	
	@ResponseBody
	@RequestMapping("updateCategory.do")
	 public ResultHelper updateCategoryById(int id,String categoryName){
		//sort/updateCategory.do?id=46&categoryName=装备学习
		//sort/updateCategory.do?id=10&categoryName=学习装备2
		return sortService.updateCategoryById(id, categoryName);
		 
	 }
	
	@ResponseBody
	@RequestMapping("deleteSpecial.do")
	 public ResultHelper deleteCategory(int id){
		 //sort/deleteSpecial.do?id=10
		return sortService.deleteCategory(id);
	 }
}
