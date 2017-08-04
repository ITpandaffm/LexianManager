/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.service;

import android.os.Handler;
import android.os.Message;

import com.example.administrator.lexianmarket.bean.city.Citys;
import com.example.administrator.lexianmarket.bean.commodity.Store;
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
public class CitysService {

    public static void getCities(String parentId,final int type, final Handler handler){
        String url = UrlConstant.PROJECT_URL+"city/getCities.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("parentId",parentId);

        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<List<Citys>> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<List<Citys>>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                msg.what=type;
                handler.sendMessage(msg);
            }
        });
    }

    public static void getStoresByCitysId(String provinceId,String cityId,
                                          String countyId, final Handler handler){
        String url = UrlConstant.PROJECT_URL+"city/getStoresByCitysId.do";
        HttpClientUtils client = new HttpClientUtils();

        Map<String,String> params =new HashMap<>();
        params.put("provinceId",provinceId);
        params.put("cityId",cityId);
        params.put("countyId",countyId);

        client.post(url,params,new HttpClientUtils.ServiceCallback() {
            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<List<Store>> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<List<Store>>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                msg.what=4;
                handler.sendMessage(msg);
            }
        });
    }


}
