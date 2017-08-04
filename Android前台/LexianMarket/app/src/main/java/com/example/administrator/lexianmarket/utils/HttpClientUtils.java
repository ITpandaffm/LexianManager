/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.utils;

import android.content.Context;

import com.example.administrator.lexianmarket.MyApplication;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;


import java.util.Map;
import java.util.Set;

/**
 *  郝伟
 */
public class HttpClientUtils {
    private static com.lidroid.xutils.HttpUtils http =
            new com.lidroid.xutils.HttpUtils(AppConfig.TIME_OUT);
    private static Context context;

    static{
        context = MyApplication.getInstance().getApplicationContext();
    }

    public static com.lidroid.xutils.HttpUtils getInstance(){
        return http;
    }

    private RequestParams params;

    private void attachRequestParams(Map<String, String> extraParams){
        if(extraParams==null){
            return;
        }
        Set<Map.Entry<String, String>> sets = extraParams.entrySet();
        this.params=new RequestParams();
        for(Map.Entry<String, String> entry : sets){

            this.params.addBodyParameter(entry.getKey(), entry.getValue());
        }
    }
    public void send(String url,RequestParams params,final ServiceCallback callback){
        http.send(HttpRequest.HttpMethod.POST, url,params,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                callback.onSuccess(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                if(callback != null){
                    callback.onFailure(e, s);
                }
            }
        });
    }

    public void get(String url, final ServiceCallback callback){
        http.send(HttpRequest.HttpMethod.GET, url, null, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                callback.onSuccess(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                if(callback != null){
                    callback.onFailure(e, s);
                }
            }
        });
    }

    public void post(String url, Map<String, String> extraParams, final ServiceCallback callback){
        attachRequestParams(extraParams);
        http.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                callback.onSuccess(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                if(callback != null){
                    callback.onFailure(e, s);
                }
            }
        });
    }

    public interface ServiceCallback {
        void onFailure(HttpException e, String errorMessage);

        void onSuccess(String result);
    }
}
