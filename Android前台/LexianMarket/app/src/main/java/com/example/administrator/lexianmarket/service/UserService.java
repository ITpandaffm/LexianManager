/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.service;

import android.os.Handler;
import android.os.Message;

import com.example.administrator.lexianmarket.bean.user.User;
import com.example.administrator.lexianmarket.helper.CashParams;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.utils.HttpClientUtils;
import com.example.administrator.lexianmarket.utils.UrlConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class UserService {

    public static void signIn(String phone,String password, final Handler handler){
        String url = UrlConstant.PROJECT_URL+"user/signIn.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("phone",phone);
        params.put("password",password);

        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper resultHelper=gson.fromJson(result,ResultHelper.class);
                Message msg=new Message();
                msg.obj=resultHelper;
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }
    public static void cash(CashParams cp, final Handler handler){
        String url = UrlConstant.PROJECT_URL+"user/cash.do";
        HttpClientUtils client = new HttpClientUtils();

        RequestParams params=new RequestParams();
        params.setContentType("application/json");
        Gson gson=new Gson();
        try {
            params.setBodyEntity(new StringEntity(gson.toJson(cp),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        client.send(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper resultHelper=gson.fromJson(result,ResultHelper.class);
                Message msg=new Message();
                msg.obj=resultHelper;
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }

    public static void getUserWithWallet(final Handler handler){
        String url = UrlConstant.PROJECT_URL+"user/getUserWithWallet.do";
        HttpClientUtils client = new HttpClientUtils();

        client.post(url,null,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<User> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<User>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }

    public static void getUser(final Handler handler){
        String url = UrlConstant.PROJECT_URL+"user/getUser.do";
        HttpClientUtils client = new HttpClientUtils();

        client.post(url,null,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<User> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<User>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }
    public static void signOut(){
        String url = UrlConstant.PROJECT_URL+"user/signOut.do";
        HttpClientUtils client = new HttpClientUtils();

        client.post(url,null,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {

            }
        });
    }
    public static void updatePassword(String newPassword,
                                      String oldPassword,final Handler handler){
        String url = UrlConstant.PROJECT_URL+"user/updatePassword.do";
        HttpClientUtils client = new HttpClientUtils();
        Map<String,String> params =new HashMap<>();
        params.put("newPassword",newPassword);
        params.put("passwd",oldPassword);
        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }

    public static void registerUser(User user,final Handler handler){
        String url = UrlConstant.PROJECT_URL+"user/registerUser.do";
        HttpClientUtils client = new HttpClientUtils();
        Map<String,String> params =new HashMap<>();
        params.put("userName",user.getUserName());
        params.put("passwd",user.getPasswd());
        params.put("phone",user.getPhone());
        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }

}
