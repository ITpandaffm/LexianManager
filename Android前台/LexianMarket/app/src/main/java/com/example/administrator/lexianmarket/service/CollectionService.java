/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.service;

import android.os.Handler;
import android.os.Message;

import com.example.administrator.lexianmarket.bean.user.CommodityCollection;
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
 * @author 郝伟
 * @version 1.0
 */
public class CollectionService {

    public static void getCommodityCollection(String pageNo, final Handler handler){
        String url = UrlConstant.PROJECT_URL+"user/collection/getCommodityCollection.do";
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
                ResultHelper<Page<CommodityCollection>> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<Page<CommodityCollection>>>() {}.getType());
                Message msg=new Message();
                msg.what=1;
                msg.obj=resultHelper;
                handler.sendMessage(msg);
            }
        });
    }

    public static void deleteCommodityCollection(CommodityCollection cc){
        String url = UrlConstant.PROJECT_URL+"user/collection/deleteCommodityCollection.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("commodityNo",cc.getCommodityNo());
        params.put("storeNo",cc.getStoreNo());

        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {

            }
        });
    }

    public static void addCommodityCollection(CommodityCollection cc){
        String url = UrlConstant.PROJECT_URL+"user/collection/addCommodityCollection.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("commodityNo",cc.getCommodityNo());
        params.put("storeNo",cc.getStoreNo());

        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {

            }
        });
    }

    public static void hasCommodityInCommodityCollection(CommodityCollection cc,
                                                         final Handler handler){
        String url = UrlConstant.PROJECT_URL
                +"user/collection/hasCommodityInCommodityCollection.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("commodityNo",cc.getCommodityNo());
        params.put("storeNo",cc.getStoreNo());

        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<Boolean> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<Boolean>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                msg.what=3;
                handler.sendMessage(msg);
            }
        });
    }

}
