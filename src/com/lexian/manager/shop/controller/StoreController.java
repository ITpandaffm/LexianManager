/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lexian.manager.shop.bean.Store;
import com.lexian.manager.shop.service.StoreService;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王子龙
 * @version 1.0
 */
@Controller
@RequestMapping("store")
@SessionAttributes(value={"isLogining"},types={Boolean.class})
public class StoreController {
	@Autowired
	private StoreService storeService;

	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	
	
	
	//Integer pageNo
	@ResponseBody
	@RequestMapping("getAllStore.do")
	public ResultHelper getAllStore(Page page){
		// TODO Auto-generated method stub
		ResultHelper result=storeService.getAllStore(page);
		//store/getAllStore.do?pageNo=2
		return result;
	}
	@ResponseBody
	@RequestMapping("addStore.do")
	public ResultHelper addStore(@Valid Store store){
		/*Store store1=new Store();
	    store1.setStoreNo("9999");*/
		/*store.setStoreNo("1002");*/
		ResultHelper result=storeService.addStore(store);
		return result;
		//store/addStore.do
	}
	@ResponseBody
	@RequestMapping("updateStore.do")
	public ResultHelper updateStore(@Valid Store store){
	/*Map<Integer,Store> map=new HashMap<Integer,Store>();*/
	/*  Integer id1=90;
	  Store store1=new Store();
	  store1.setStoreName("hahah");*/
		ResultHelper result=storeService.updateStore(store);
		return result;
		//store/updateStore.do?storeNo=1001&storeName=aaa
	}
	@ResponseBody
	@RequestMapping("getStoreByStoreNo.do")
	public ResultHelper getStoreByStoreNo(String storeNo){
		ResultHelper result=storeService.getStoreByStoreNo(storeNo);
		return result;
		//store/getStoreByStoreNo.do?storeNo=1001
	}
}
