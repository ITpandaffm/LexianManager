/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.mall.commodity.bean.Commodity;
import com.lexian.mall.user.bean.CommodityCollection;
import com.lexian.mall.user.dao.CommodityCollectionDao;
import com.lexian.mall.user.service.CommodityCollectionService;
import com.lexian.utils.Constant;
import com.lexian.utils.UrlContstant;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
@Service
public class CommodityCollectionServiceImpl implements CommodityCollectionService{

	@Autowired
	private CommodityCollectionDao commodityCollectionDao ; 
	@Override
	public ResultHelper getCommodityCollection(Integer pageNo, String userId) {
		
		Page page=new Page();
		page.setPageNo(pageNo);
		Map<String,Object> params=new HashMap<>();
		params.put("page", page);
		params.put("userId", userId);
		
		List<CommodityCollection> cc=commodityCollectionDao.getCommodityCollectionPage(params);
		
		for(CommodityCollection c:cc){
			Commodity commodity=c.getCommodity();
			String url=UrlContstant.QINIU_ADDRESS+commodity.getPictureUrl();
			try {
				URLEncoder.encode(url, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			commodity.setPictureUrl(url);
		}
		page.setData(cc);
		return new ResultHelper(Constant.CODE_SUCCESS,page);
	}
	@Override
	public ResultHelper addCommodityCollection(CommodityCollection cc) {
		
		Integer count=commodityCollectionDao.hasCommodityInCommodityCollection(cc);
		boolean isExist=count>0?true:false;
		if(!isExist){
			cc.setCollectTime(new Date());
			commodityCollectionDao.addCommodityCollection(cc);
		}
		return new ResultHelper(Constant.CODE_SUCCESS);
	}
	@Override
	public ResultHelper deleteCommodityCollection(CommodityCollection cc) {
		commodityCollectionDao.deleteCommodityCollection(cc);
		return new ResultHelper(Constant.CODE_SUCCESS);
	}
	
	@Override
	public ResultHelper hasCommodityInCommodityCollection(CommodityCollection cc) {
		Integer count=commodityCollectionDao.hasCommodityInCommodityCollection(cc);
		boolean isExist=count>0?true:false;
		return new ResultHelper(Constant.CODE_SUCCESS,isExist);
	}
}
