/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.bean.user.User;
import com.example.administrator.lexianmarket.helper.LoginHelper;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.UserService;
import com.example.administrator.lexianmarket.utils.Constant;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王晨昕
 * @version 1.0
 */
public class LoginActivity extends BaseActivity {

    private EditText etPhone;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvLoginRegister;
    private TextView tvGoForget;
    private CheckBox autoLogin,rememberPwd;
    private SharedPreferences sp;

    private static final int LOGIN_RESPONSE=1;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case LOGIN_RESPONSE:

                    ResultHelper<User> result= (ResultHelper<User>) msg.obj;
                    if(result.getCode()== Constant.CODE_SUCCESS){
                        LoginHelper.isLogin=true;

                        Toast.makeText(LoginActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        if(result.getCode()==Constant.CODE_STATE_FORBID){
                            Toast.makeText(LoginActivity.this, R.string.login_forbid,
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginActivity.this, R.string.login_fail,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;

            }


            super.handleMessage(msg);
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        if(LoginHelper.isLogin){
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initData();
        setListener();

    }

    private void initData() {

        if (sp.getBoolean("IS_CHECK",false)){
            rememberPwd.setChecked(true);
            etPhone.setText(sp.getString("USER_NAME",""));
            etPassword.setText(sp.getString("PASSWORD",""));
            if (sp.getBoolean("AUTO_ISCHECK",false)){
                autoLogin.setChecked(true);
            }
        }
    }

    private void setListener() {
        btnLogin.setOnClickListener(new OnLoginClickListener());
        View.OnClickListener clickListener = new OnGoOthersActivity();
        tvLoginRegister.setOnClickListener(clickListener);
        tvGoForget.setOnClickListener(clickListener);
        autoLogin.setOnCheckedChangeListener(new OnAutoLoginCheckedChangeListener());
        rememberPwd.setOnCheckedChangeListener(new OnPwdCheckedChangeListener());
    }

    private void initViews() {

        ((TextView)findViewById(R.id.common_title_content)).setText(R.string.login_title);

        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etPassword =(EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvLoginRegister = (TextView) findViewById(R.id.tv_login_register);
        tvGoForget = (TextView) findViewById(R.id.tv_go_forget);
        autoLogin= (CheckBox) findViewById(R.id.cb_auto_login);
        rememberPwd=(CheckBox) findViewById(R.id.cb_remember_password);

    }

    private class OnLoginClickListener implements View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

            boolean isValid=checkParams();

            if(isValid){
                String phone= etPhone.getText().toString();
                String password= etPassword.getText().toString();

                if (rememberPwd.isChecked()){
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("USER_NAME",phone);
                    editor.putString("PASSWORD",password);
                    editor.commit();
                }
                UserService.signIn(phone,password,handler);
            }

        }
    }

    private boolean checkParams() {
        String phone= etPhone.getText().toString();
        String password= etPassword.getText().toString();
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, R.string.params_not_empty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!phone.matches("[0-9]+")){
            Toast.makeText(LoginActivity.this, R.string.login_fail_phone_invalid,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(phone.length() != 11){
            Toast.makeText(LoginActivity.this, R.string.login_fail_phonenumber,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    private class OnGoOthersActivity implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            goOthersActivity(v.getId());
        }
    }

    private void goOthersActivity(int id) {
        Intent intent = null;
        switch (id){
            case R.id.tv_login_register:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                break;
            case R.id.tv_go_forget:
                intent=new Intent(LoginActivity.this,ForgetActivity.class);
            default:
                break;
        }
        if(intent != null){
            startActivity(intent);
            finish();
        }
    }

    private class OnPwdCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (rememberPwd.isChecked()){
                sp.edit().putBoolean("IS_CHECK",true).commit();
            }else {
                sp.edit().putBoolean("IS_CHECK",false).commit();
            }

        }
    }

    private class OnAutoLoginCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (autoLogin.isChecked()){
                sp.edit().putBoolean("AUTO_ISCHECK",true).commit();
            }else {
                sp.edit().putBoolean("AUTO_ISCHECK",false).commit();
            }
        }
    }

}
