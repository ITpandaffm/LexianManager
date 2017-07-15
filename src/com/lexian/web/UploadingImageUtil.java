package com.lexian.web;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class UploadingImageUtil {

		// 构造一个带指定Zone对象的配置类
		private static Configuration cfg = new Configuration(Zone.zone2());
		// ...其他参数参考类注释
		private static UploadManager uploadManager = new UploadManager(cfg);
		// ...生成上传凭证，然后准备上传
		private static String accessKey = "cF_2ALl43aKI4BtLkaZ2JIJnqQ-c3yE_ew9_Rfe9";
		private static String secretKey = "jLgHePbyHruskcqQC3MbqGz2tCNVF_gy2v66dqBd";
		
		private static String bucket = "lexianmanager";// 上传到指定的七牛云存储的一个空间中
		private static String key = null;// 默认不指定key的情况下，以文件内容的hash值作为文件名（上传后的文件名）
		
		public static String upload(CommonsMultipartFile file) throws UnsupportedEncodingException {
			String upToken="";
			try{
				long startTime =System.currentTimeMillis();
				key = startTime+file.getOriginalFilename();
				//把文件转化为字节数组
				System.out.println(key);
				InputStream is = file.getInputStream();
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] b = new byte[1024];
				int len = -1;
				while ((len = is.read(b)) != -1) {
					bos.write(b, 0, len);
				}
				byte[] uploadBytes = bos.toByteArray();
				//进行七牛的操作，不懂去七牛的sdk上看
				Auth auth = Auth.create(accessKey, secretKey);
				upToken = auth.uploadToken(bucket);
				//默认上传接口回复对象
				DefaultPutRet putRet;
				//进行上传操作，传入文件的字节数组，文件名，上传空间，得到回复对象
				Response response = uploadManager.put(uploadBytes, key, upToken);
				putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				System.out.println(putRet.key);//key文件名

				System.out.println(putRet.hash);
			}catch (Exception e) {
				
			}
			
			//得到上传文件的文件名，并赋值给key作为七牛存储的文件名
			return key;
		}


/*	 public static boolean papers(MultipartFile imgFile){
	     if(imgFile !=null){
	         System.out.println("上传图片大小是："+imgFile.getSize());
	         if(imgFile.getSize()<512000){
	         List<String> fileTypes = new ArrayList<String>();  
	            fileTypes.add("jpg");  
	            fileTypes.add("jpeg");  
	            fileTypes.add("png"); 
	            String fileName = imgFile.getOriginalFilename();
	            //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
	             String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());  
	             //对扩展名进行小写转换  
	             ext = ext.toLowerCase();  
	            if(fileTypes.contains(ext)) { //如果扩展名属于允许上传的类型，则创建文件  
	                return true;
	             }else{
	                return false;
	             }
	         }else{
	             return false;
	         }
	     }else{
	         return false;
	     }
	}*/
}