package com.lexian.manager.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lexian.manager.goods.service.CategoryViewService;
import com.lexian.web.ResultHelper;

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
	public ResultHelper getAllCategoryView(Integer pageNo){
		ResultHelper result=cateViewService.getAllCategoryView(pageNo);
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
	public ResultHelper getFirstCategoryView(Integer pageNo){
		ResultHelper result=cateViewService.getFirstCategoryView(pageNo);
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
	public ResultHelper getSecondCategoryView(Integer pageNo){
		ResultHelper result=cateViewService.getSecondCategoryView(pageNo);
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
	public ResultHelper getThirdCategoryView(Integer pageNo){
		ResultHelper result=cateViewService.getThirdCategoryView(pageNo);
		return result;
		//categoryView/getThirdCategoryView.do?pageNo=1
	}
}
