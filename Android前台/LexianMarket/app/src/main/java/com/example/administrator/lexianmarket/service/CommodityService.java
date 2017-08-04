/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.service;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.administrator.lexianmarket.bean.commodity.CommodityStore;
import com.example.administrator.lexianmarket.bean.commodity.Special;
import com.example.administrator.lexianmarket.helper.Page;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.utils.HttpClientUtils;
import com.example.administrator.lexianmarket.utils.UrlConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class CommodityService {

    public static void getCommodityStore(Integer pageNo,final int categoryId,
                                         final Handler handler){
        String url= UrlConstant.PROJECT_URL
                +"sortCommodity/getCommoditiesByCategoryId.do?categoryId="+categoryId
                +"&pageNo="+pageNo;
        HttpClientUtils client = new HttpClientUtils();

        client.get(url,new HttpClientUtils.ServiceCallback(){

            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<Page<CommodityStore>> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<Page<CommodityStore>>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                Log.e("CategoryID", ""+categoryId);
                Log.v("CommodityStore",resultHelper.getData().toString());
                Log.v("CommodityStore",resultHelper.toString());
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }

    public static void getCommodityStoreByStoreNo(Integer pageNo,String storeNo,
                                                  final Handler handler){
        String url= UrlConstant.PROJECT_URL
                +"specialCommodity/getCommodityStoreByStoreNo.do?storeNo="+storeNo
                +"&pageNo="+pageNo;
        HttpClientUtils client = new HttpClientUtils();

        client.get(url,new HttpClientUtils.ServiceCallback(){

            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<Page<CommodityStore>> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<Page<CommodityStore>>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                Log.v("CommodityStore",resultHelper.getData().toString());
                Log.v("CommodityStore",resultHelper.toString());
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }

    public static void getCommodity(String commodityNo, String storeNo,final Handler handler){
        String url = UrlConstant.PROJECT_URL+"sortCommodity/getCommodityStore.do";

        HttpClientUtils client = new HttpClientUtils();
        Map<String,String> params =new HashMap<>();
        params.put("commodityNo",commodityNo);
        params.put("storeNo",storeNo);

        client.post(url,params,new HttpClientUtils.ServiceCallback(){

            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<CommodityStore> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<CommodityStore>>() {}.getType());

                Message msg=new Message();
                msg.obj=resultHelper;
                Log.v("hello",resultHelper.getData().toString());
                Log.v("hello",resultHelper.toString());
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }
    public static void getSpecials(Integer pageNo, final Handler handler){
        String url= UrlConstant.PROJECT_URL+"specialCommodity/getSpecials.do?pageNo="+pageNo;
        HttpClientUtils client = new HttpClientUtils();

        client.get(url,new HttpClientUtils.ServiceCallback(){

            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<Page<Special>> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<Page<Special>>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }
}
