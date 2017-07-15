package com.lexian.manager.goods.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.lexian.utils.Constant;
import com.lexian.utils.UrlContant;
import com.lexian.web.ResultHelper;
import com.lexian.web.UploadingImageUtil;


@Controller
@RequestMapping("UploadPicture")
@SessionAttributes(value = { "isLogining" }, types = { Boolean.class })
public class UploadPictrueController {
	
	@ResponseBody
	@RequestMapping("uploadMainPic.do")
	public ResultHelper uploadMainPic(HttpServletRequest request)throws IOException{
		String newPictureUrl=null;
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest =(MultipartHttpServletRequest) request;
			Iterator<String> ite =multiRequest.getFileNames();
			while(ite.hasNext()){
				MultipartFile file =multiRequest.getFile(ite.next());
				CommonsMultipartFile fi = (CommonsMultipartFile) file;
				String newName = UploadingImageUtil.upload(fi);
				newPictureUrl = UrlContant.qiNiuUrl + "/" + newName;
			}
		}
		
		return new ResultHelper(Constant.code_success,newPictureUrl);
		}
	
	
	@ResponseBody
	@RequestMapping("uploadManyPic.do")
	public ResultHelper uploadManyPic(HttpServletRequest request)throws IOException{
		
		List<String> newPictureUrl = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest =(MultipartHttpServletRequest) request;
			Iterator<String> ite =multiRequest.getFileNames();
			while(ite.hasNext()){
				MultipartFile file =multiRequest.getFile(ite.next());
				CommonsMultipartFile fi = (CommonsMultipartFile) file;
				String newName = UploadingImageUtil.upload(fi);
				newPictureUrl.add(UrlContant.qiNiuUrl + "/" + newName) ;
			}
		}
		
		return new ResultHelper(Constant.code_success,newPictureUrl);
		}
}

