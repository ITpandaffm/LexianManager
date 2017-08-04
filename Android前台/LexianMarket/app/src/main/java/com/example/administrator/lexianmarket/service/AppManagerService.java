/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.service;

import android.os.Handler;
import android.os.Message;

import com.example.administrator.lexianmarket.bean.version.AppManager;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.utils.HttpClientUtils;
import com.example.administrator.lexianmarket.utils.UrlConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王子龙
 * @version 1.0
 */
public class AppManagerService {
 public static void getAppManager(final Handler handler){
     String url = UrlConstant.PROJECT_URL+"version/getLastVersion.do";
     HttpClientUtils client = new HttpClientUtils();
     client.post(url,null,new HttpClientUtils.ServiceCallback() {
         @Override
         public void onFailure(HttpException e, String errorMessage) {

         }
         @Override
         public void onSuccess(String result) {
             Gson gson = new Gson();
             ResultHelper<AppManager> resultHelper=gson
                     .fromJson(result,new TypeToken<ResultHelper<AppManager>>() {}.getType());
             Message msg=new Message();
             msg.obj=resultHelper;
             msg.what=1;
             handler.sendMessage(msg);
         }
     });
 }
}
