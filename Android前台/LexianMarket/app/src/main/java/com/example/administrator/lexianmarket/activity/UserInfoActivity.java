/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.bean.user.User;
import com.example.administrator.lexianmarket.helper.LoginHelper;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.UserService;
import com.example.administrator.lexianmarket.utils.BitmapUtilsHelper;
import com.example.administrator.lexianmarket.utils.Constant;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class UserInfoActivity extends BaseActivity {
       /*
     * Exceptions found during parsing
     *
     * References a layout (@layout/item_common_header)
     */

    // Content View Elements
    private static final int OBTAINED_USER=1;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {


            switch (msg.what){
                case OBTAINED_USER:

                    ResultHelper<User> result= (ResultHelper<User>) msg.obj;
                    if(result.getCode()== Constant.CODE_SUCCESS){
                        bindUserInfo(result.getData());
                    }
                    break;
            }

            super.handleMessage(msg);
        }
    };

    private void bindUserInfo(User user) {
        tvUsername.setText(user.getUserName());
        tvPhone.setText(user.getPhone());
        BitmapUtilsHelper.getBitmapUtils(this).display(ivUserIcon,user.getPortrait());
    }

    private ImageView ivUserIcon;
    private TextView tvUsername;
    private TextView tvPhone;
    private RelativeLayout rlChangePassword;
    private TextView commonTitleRightText;
    private Button btnLogout;

    // End Of Content View Elements

    private void initViews() {
        setContentView(R.layout.activity_user_info);
        ((TextView)findViewById(R.id.common_title_content)).setText(R.string.person_information);
        commonTitleRightText = (TextView) findViewById(R.id.common_title_right_text);
        ivUserIcon = (ImageView) findViewById(R.id.iv_user_icon);
        tvUsername = (TextView) findViewById(R.id.tv_username);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        rlChangePassword = (RelativeLayout) findViewById(R.id.rl_change_password);
        btnLogout = (Button) findViewById(R.id.btn_logOut);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        setListener();
        initData();
    }

    private void initData() {
        commonTitleRightText.setText(R.string.first_page);
        UserService.getUser(handler);
    }

    private void setListener() {
        rlChangePassword.setOnClickListener(new GoOtherActivityClickListener());
        btnLogout.setOnClickListener(new OnSignOutListener());
        commonTitleRightText.setOnClickListener(new GoFirstPageClickListener());
    }

    private class GoOtherActivityClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(UserInfoActivity.this,UpdatePasswordActivity.class);
            startActivity(intent);
        }
    }

    private class OnSignOutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            UserService.signOut();
            Toast.makeText(UserInfoActivity.this, R.string.exit_success, Toast.LENGTH_SHORT).show();
            LoginHelper.isLogin=false;
            finish();
        }
    }

    private class GoFirstPageClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UserInfoActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
