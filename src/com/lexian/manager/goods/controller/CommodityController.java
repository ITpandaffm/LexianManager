package com.lexian.manager.goods.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.lexian.manager.goods.bean.Commodity;
import com.lexian.manager.goods.service.CommodityService;
import com.lexian.web.ResultHelper;

@Controller
@RequestMapping("commodity")
@SessionAttributes(value={"isLogining"},types={Boolean.class})
public class CommodityController {

	@Autowired
	private CommodityService commodityService;
	


	public CommodityService getCommodityService() {
		return commodityService;
	}

	public void setCommodityService(CommodityService commodityService) {
		this.commodityService = commodityService;
	}
	
	@ResponseBody
	@RequestMapping("getCommodities.do")
	public ResultHelper getCommodities(Integer pageNo){
		
		ResultHelper result= commodityService.getCommodities(pageNo);
		//commodity/getCommodities.do?pageNo=1
		return result;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("getCommodityByName.do")
	public ResultHelper getCommodityByName(String name){
		ResultHelper result=commodityService.getCommodityByName(name);
		
		return result;
		//
	}
	
	@ResponseBody
	@RequestMapping("getCommodityBycommodityNo.do")
	public ResultHelper getCommodityBycommodityNo(String commodityNo){
		
		ResultHelper result=commodityService.getCommodityBycommodityNo(commodityNo);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("getCommodityById.do")
	public ResultHelper getCommodityById(int id){
		ResultHelper result=commodityService.getCommodityById(id);
		return result;
		//commodity/getCommodityById.do?id=40
	}
	
	@ResponseBody
	@RequestMapping("updateCommodity.do")
	public ResultHelper updateCommodity(Commodity commodity,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
		//Commodity commodity=new Commodity();
		 boolean bol=true;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest =(MultipartHttpServletRequest) request;
			Iterator<String> ite =multiRequest.getFileNames();
		
			while(ite.hasNext()){
				if(bol=true){
				MultipartFile file =multiRequest.getFile(ite.next());
				String newName=commodity.getCommodityNo()+file.getOriginalFilename();
				String realPath = request.getSession().getServletContext().getRealPath("/");
				String path="commodity"+System.getProperty("file.separator")+newName;
				commodity.setPictureUrl("/"+path);
				file.transferTo(new File(realPath+path));
				bol=false;
			}else{
				MultipartFile nextFile = multiRequest.getFile(ite.next());
				String nextFileName=commodity.getCommodityNo()+nextFile.getOriginalFilename();
				String nextRealPath = request.getSession().getServletContext().getRealPath("/");
				String nextpath="commodity"+System.getProperty("file.separator")+nextFileName;
				commodityService.updateCommodityPicture(commodity.getCommodityNo(), "/"+nextpath);
				nextFile.transferTo(new File(nextRealPath+nextpath));
				
			}
			}
		}
		
		ResultHelper result=commodityService.updateCommodity(commodity);
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping("addCommodity.do")
	public ResultHelper addCommodity(){
		Commodity commodity = new Commodity();
		commodity.setName("wangzilongchenhao");
		commodity.setCategoryId(46);
		commodity.setCommodityNo("987987987");
		commodity.setIntroduce("chenhaochenhao");
		ResultHelper result=commodityService.addCommodity(commodity);
		return result;
		//commodity/addCommodity.do
	}
}
