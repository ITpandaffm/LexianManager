/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.bean.version.AppManager;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.AppManagerService;
import com.example.administrator.lexianmarket.utils.Constant;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王子龙
 * @version 1.0
 */
public class AppManagerActivity extends BaseActivity {
    private ImageView commonTitleLeftBack;
    private TextView appName;
    private TextView versionNum;
    private TextView appUrl;
    private TextView log;
    private static final int OBTAINED_APP=1;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case OBTAINED_APP:
                    ResultHelper<AppManager> result= (ResultHelper<AppManager>) msg.obj;

                    if(result.getCode()== Constant.CODE_SUCCESS){
                        bindAppData(result);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private void bindAppData(ResultHelper<AppManager> result) {
        AppManager appManager=result.getData();
        appName.setText(appManager.getAppName());
        versionNum.setText(appManager.getVersionNumber());
        appUrl.setText(appManager.getAppUrl());
        log.setText(appManager.getLog());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /*setListener();*/
        initViews();
        initdata();
    }
    private void setListener() {
        commonTitleLeftBack.setOnClickListener(new OnBackClickListener());
    }
    private  void initdata(){
        ((TextView)findViewById(R.id.common_title_content)).setText(R.string.mine_menu4_text);
        AppManagerService.getAppManager(handler);
    }
    private void initViews() {
         setContentView(R.layout.activity_app_manager);
         commonTitleLeftBack = (ImageView) findViewById(R.id.common_title_left_back);
         appName =(TextView) findViewById(R.id.appname);
         versionNum=(TextView) findViewById(R.id.versionNum);
         appUrl=(TextView) findViewById(R.id.appUrl);
         log=(TextView) findViewById(R.id.log);
    }
    private class OnBackClickListener implements View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            finish();
        }
    }
}
