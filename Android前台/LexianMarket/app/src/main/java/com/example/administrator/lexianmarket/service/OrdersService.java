/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.service;

import android.os.Handler;
import android.os.Message;

import com.example.administrator.lexianmarket.bean.user.Orders;
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
public class OrdersService {

    public static void getOrders(String pageNo,String state, final Handler handler){
        String url = UrlConstant.PROJECT_URL+"user/order/getOrders.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("pageNo",pageNo);
        params.put("state",state);


        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<Page<Orders>> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<Page<Orders>>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }
    public static void getOrderDetail(String id, final Handler handler){
        String url = UrlConstant.PROJECT_URL+"user/order/getOrderDetail.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("id",id);

        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<Orders> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<Orders>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }


}
