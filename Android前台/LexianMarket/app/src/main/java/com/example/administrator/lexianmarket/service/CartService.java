/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.service;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.administrator.lexianmarket.bean.user.Trolley;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.utils.HttpClientUtils;
import com.example.administrator.lexianmarket.utils.UrlConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class CartService {
    public static void getTrolleys(String pageNo, final Handler handler){
        String url = UrlConstant.PROJECT_URL+"user/trolley/getTrolleys.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("pageNo",pageNo);

        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<List<Trolley>> resultHelper=gson
                        .fromJson(result,new TypeToken<ResultHelper<List<Trolley>>>() {}.getType());
                Message msg=new Message();
                msg.what=1;
                msg.obj=resultHelper;
                handler.sendMessage(msg);
            }
        });
    }

    public static void addCommodityToTrolley(Trolley trolley){
        String url = UrlConstant.PROJECT_URL+"user/trolley/addCommodityToTrolley.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("storeNo",trolley.getStoreNo());
        params.put("commodityNo",trolley.getCommodityNo());
        params.put("amont",trolley.getAmont()+"");

        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {

            }
        });
    }

    public static void hasCommodityInTrolley(Trolley trolley,final Handler handler){
        String url = UrlConstant.PROJECT_URL+"user/trolley/hasCommodityInTrolley.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("storeNo",trolley.getStoreNo());
        params.put("commodityNo",trolley.getCommodityNo());

        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<Boolean> resultHelper=gson
                        .fromJson(result,new TypeToken<ResultHelper<Boolean>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                Log.v("CommodityStore",resultHelper.getData().toString());
                Log.v("CommodityStore",resultHelper.toString());
                msg.what=2;
                handler.sendMessage(msg);
            }
        });
    }

    public static void updateTrolley(Trolley trolley){
        String url = UrlConstant.PROJECT_URL+"user/trolley/updateTrolley.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("id",trolley.getId()+"");
        params.put("storeNo",trolley.getStoreNo());
        params.put("commodityNo",trolley.getCommodityNo());
        params.put("amont",trolley.getAmont()+"");

        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {

            }
        });
    }

    public static void clearTrolley(){
        String url = UrlConstant.PROJECT_URL+"user/trolley/clearTrolley.do";
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



    public static void deleteTrolley(String id){
        String url = UrlConstant.PROJECT_URL+"user/trolley/deleteTrolley.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("id",id);

        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {

            }
        });
    }
}
