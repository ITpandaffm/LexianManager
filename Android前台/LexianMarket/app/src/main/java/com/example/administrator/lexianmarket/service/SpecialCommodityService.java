/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.service;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.administrator.lexianmarket.bean.commodity.Commodity;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.utils.HttpClientUtils;
import com.example.administrator.lexianmarket.utils.UrlConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;

import java.util.List;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class SpecialCommodityService {

    public static void getSpecialCommodity(int id, final Handler handler){
        String url= UrlConstant.PROJECT_URL
                +"specialCommodity/getSpecialCommodity.do?specialId="+id;
        HttpClientUtils client = new HttpClientUtils();

        client.get(url,new HttpClientUtils.ServiceCallback(){

            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<List<Commodity>> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<List<Commodity>>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                Log.v("specialCommodity",resultHelper.getData().toString());
                Log.v("specialCommodity",resultHelper.toString());
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }

}
