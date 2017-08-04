/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.service;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.administrator.lexianmarket.bean.commodity.Category;
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
public class CategoryService {
    public  static void getFirstCategory(final Handler handler){
        String url= UrlConstant.PROJECT_URL+UrlConstant.SORT_COMMODITY_GET_FIRST_CATEGORY_DO;
        HttpClientUtils client = new HttpClientUtils();

        client.get(url,new HttpClientUtils.ServiceCallback(){

            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();

                ResultHelper<List<Category>> resultHelper= gson.fromJson(result,
                        new TypeToken<ResultHelper<List<Category>>>() {}.getType());

                Message msg=new Message();
                msg.obj=resultHelper;
                Log.v("Category",resultHelper.getData().toString());
                Log.v("Category",resultHelper.toString());
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
    }

    public static void getSecondCategory(int parentId,final Handler handler){
        String url= UrlConstant.PROJECT_URL
                +"sortCommodity/getSecondCategory.do?parentId="+parentId;
        HttpClientUtils client = new HttpClientUtils();

        client.get(url,new HttpClientUtils.ServiceCallback(){

            @Override
            public void onFailure(HttpException e, String errorMessage) {

            }

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ResultHelper<List<Category>> resultHelper=gson.fromJson(result,
                        new TypeToken<ResultHelper<List<Category>>>() {}.getType());
                Message msg=new Message();
                msg.obj=resultHelper;
                Log.v("SecondCategory",resultHelper.getData().toString());
                Log.v("SecondCategory",resultHelper.toString());
                msg.what=2;
                handler.sendMessage(msg);
            }
        });
    }
}
