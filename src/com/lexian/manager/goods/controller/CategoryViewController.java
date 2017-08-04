/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lexian.manager.goods.service.CategoryViewService;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
@Controller
@RequestMapping("categoryView")
@SessionAttributes(value={"isLogining"},types={Boolean.class})
public class CategoryViewController {
	
	@Autowired
	private CategoryViewService cateViewService;
	
	
	public CategoryViewService getCateViewService() {
		return cateViewService;
	}

	public void setCateViewService(CategoryViewService cateViewService) {
		this.cateViewService = cateViewService;
	}
	/**
	 * 
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAllCategoryView.do")
	public ResultHelper getAllCategoryView(Page page){
		ResultHelper result=cateViewService.getAllCategoryView(page);
		return result;
		//categoryView/getAllCategoryView.do?pageNo=1
	}
	/**
	 * 一级菜单
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getFirstCategoryView.do")
	public ResultHelper getFirstCategoryView(Page page){
		ResultHelper result=cateViewService.getFirstCategoryView(page);
		return result;
		//categoryView/getFirstCategoryView.do?pageNo=1
	}
	/**
	 * 二级菜单
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getSecondCategoryView.do")
	public ResultHelper getSecondCategoryView(Page page){
		ResultHelper result=cateViewService.getSecondCategoryView(page);
		return result;
		//categoryView/getSecondCategoryView.do?pageNo=1
	}
	/**
	 * 三级菜单
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getThirdCategoryView.do")
	public ResultHelper getThirdCategoryView(Page page){
		ResultHelper result=cateViewService.getThirdCategoryView(page);
		return result;
		//categoryView/getThirdCategoryView.do?pageNo=1
	}
}
