/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.plate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.manager.plate.service.SpecialService;
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
@RequestMapping("special")
public class SpecialController {
	
	@Autowired
	private SpecialService specialService;

	public SpecialService getSpecialService() {
		return specialService;
	}
	public void setSpecialService(SpecialService specialService) {
		this.specialService = specialService;
	}
	/**
	 * 获取所有的板块id 板块名称
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getSpecial.do")
	public ResultHelper getSpecial(Page page){
		ResultHelper result =specialService.getSpecial(page);
		return result;
		//special/getSpecial.do?pageNo=1
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateSpecial.do")
	public ResultHelper updateSpecial(int id,String name){
		ResultHelper result = specialService.updateSpecial(id, name);
		return result;
		//special/updateSpecial.do?id=17&name=王子龙
	}
	
	/**
	 * id 是special的id，删除某个板块
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteSpecial.do")
	public ResultHelper deleteSpecial(int id){
		ResultHelper result = specialService.deleteSpecial(id);
		return result;
		//special/deleteSpecial.do?id=6
	}
	/**
	 *添加，首先要判断是否已经存在
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addSpecial.do")
	public ResultHelper addSpecial(String name){
		ResultHelper result = specialService.addSpecial(name);
		return result;
		//special/addSpecial.do?name=陈浩
	}
	
}
