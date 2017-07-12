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

	/**
	 * 查询所有的category
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAllCategories.do")
	 public ResultHelper getAllCategories(){
		ResultHelper result=sortService.getAllCategories();
		return result;
		//sort/getAllCategories.do
	 }
	/**
	 * 
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCategoryView.do")
	 public ResultHelper getCategoryView(Integer pageNo){
		ResultHelper result=sortService.getAllCategories();
		return result;
		//sort/getCategoryView.do
	 }
	/**
	 * 通过categoryName查询
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
	 * 添加category
	 * @param category
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addCategory.do")
	 public ResultHelper addCategory(Category category){
		//
		return sortService.addCategory(category);
		 
	 }
	
	/**
	 * 修改category
	 * @param id
	 * @param categoryName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateCategory.do")
	 public ResultHelper updateCategoryById(int id,String categoryName){
		//sort/updateCategory.do?id=46&categoryName=装备学习
		//sort/updateCategory.do?id=10&categoryName=学习装备2
		return sortService.updateCategoryById(id, categoryName);
		 
	 }
	/**
	 * 删除category
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteSpecial.do")
	 public ResultHelper deleteCategory(int id){
		 //sort/deleteSpecial.do?id=10
		return sortService.deleteCategory(id);
	 }
}
