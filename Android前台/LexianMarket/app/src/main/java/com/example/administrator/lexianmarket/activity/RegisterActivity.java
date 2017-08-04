/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
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
public class RegisterActivity extends BaseActivity {

    private EditText etRegisterPhone;
    private EditText etRegisterPassword;
    private EditText etRegisterPwdConfirm;
    private EditText etRegisterName;
    private Button btnRegister;
    private static final int REGISTER_MESSAGE=1;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case REGISTER_MESSAGE:

                    ResultHelper result= (ResultHelper) msg.obj;
                    showInfo(result);
                    break;
            }


            super.handleMessage(msg);
        }
    };

    private void showInfo(ResultHelper result) {

        if(result.getCode()== Constant.CODE_SUCCESS){
            LoginHelper.isLogin=true;
            Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show();
            finish();
        }else if(result.getCode()== Constant.CODE_PHONE_USED){
            Toast.makeText(this, R.string.this_phone_used, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        setListener();
    }

    private void initView() {
        ((TextView)findViewById(R.id.common_title_content)).setText(R.string.user_register);

        etRegisterPhone = (EditText) findViewById(R.id.et_register_phone);
        etRegisterPassword = (EditText) findViewById(R.id.et_register_password);
        etRegisterPwdConfirm = (EditText) findViewById(R.id.et_register_pwd_confirm);
        etRegisterName = (EditText) findViewById(R.id.et_register_name);
        btnRegister = (Button) findViewById(R.id.btn_register);

    }

    private void setListener() {
        btnRegister.setOnClickListener(new OnRegisterListener());
    }

    private class OnRegisterListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            boolean isValid = checkParams();
            if(isValid){
                String phone = etRegisterPhone.getText().toString();
                String pwd = etRegisterPassword.getText().toString();
                String userName= etRegisterName.getText().toString();
                User user=new User();
                user.setPasswd(pwd);
                user.setPhone(phone);
                user.setUserName(userName);
                UserService.registerUser(user,handler);
            }
        }
    }

    private boolean checkParams() {
        String name = etRegisterName.getText().toString();
        String phone = etRegisterPhone.getText().toString();
        String pwd = etRegisterPassword.getText().toString();
        String pwd_confirm = etRegisterPwdConfirm.getText().toString();

        if("".equals(name)){
            Toast.makeText(RegisterActivity.this, R.string.fail_username_empty,
                    Toast.LENGTH_SHORT).show();
        }else if("".equals(phone)){
            Toast.makeText(RegisterActivity.this, R.string.fail_phonenumber_empty,
                    Toast.LENGTH_SHORT).show();
        }else if(!phone.matches("[0-9]+")){
            Toast.makeText(RegisterActivity.this, R.string.fail_phonenumber_invalid,
                    Toast.LENGTH_SHORT).show();
        }else if(phone.length() != 11){
            Toast.makeText(RegisterActivity.this, R.string.fail_phonenumber,
                    Toast.LENGTH_SHORT).show();
        }else if(pwd.length() < 6){
            Toast.makeText(RegisterActivity.this, R.string.fail_password_min,
                    Toast.LENGTH_SHORT).show();
        }else if(pwd.length() > 12){
            Toast.makeText(RegisterActivity.this, R.string.fail_password_max,
                    Toast.LENGTH_SHORT).show();
        }else if(!pwd_confirm.equals(pwd)){
            Toast.makeText(RegisterActivity.this, R.string.fail_password_two,
                    Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
        return false;
    }
}
