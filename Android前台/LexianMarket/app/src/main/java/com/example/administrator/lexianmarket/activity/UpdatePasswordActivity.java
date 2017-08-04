/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.UserService;
import com.example.administrator.lexianmarket.utils.Constant;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class UpdatePasswordActivity extends BaseActivity {

    private ImageView ivPassword;
    private EditText etPassword;
    private ImageView ivPasswordAggin;
    private EditText etPasswordAgain;
    private ImageView ivRegisterPwdConfirm;
    private EditText etPwdConfirm;
    private Button btnUpdate;
    private static final int UPDATE_MESSAGE=1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case UPDATE_MESSAGE:
                    ResultHelper result= (ResultHelper) msg.obj;
                    showMessage(result);
                    break;
            }


            super.handleMessage(msg);
        }
    };

    private void showMessage(ResultHelper result) {
        switch (result.getCode()){
            case Constant.CODE_SUCCESS:
                Toast.makeText(this, R.string.modify_success, Toast.LENGTH_SHORT).show();
                finish();
                break;
            case Constant.CODE_PASSWORD_ERROR:
                Toast.makeText(this, result.getData().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        setListener();
    }

    private void setListener() {
        btnUpdate.setOnClickListener(new OnUpdateClickListener());
    }

    private void initViews() {
        setContentView(R.layout.activity_update_password);
        ((TextView)findViewById(R.id.common_title_content)).setText(R.string.modify_password);
        ivPassword = (ImageView) findViewById(R.id.iv_password);
        etPassword = (EditText) findViewById(R.id.et_password);
        ivPasswordAggin = (ImageView) findViewById(R.id.iv_password_aggin);
        etPasswordAgain = (EditText) findViewById(R.id.et_password_again);
        ivRegisterPwdConfirm = (ImageView) findViewById(R.id.iv_register_pwd_confirm);
        etPwdConfirm = (EditText) findViewById(R.id.et_pwd_confirm);
        btnUpdate = (Button) findViewById(R.id.btn_update);

    }


    private class OnUpdateClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            boolean isValid=checkParams();
            String password= etPassword.getText().toString();
            String newPass= etPwdConfirm.getText().toString();
            if(isValid){
                UserService.updatePassword(newPass,password,handler);
            }else{
                Toast.makeText(UpdatePasswordActivity.this, R.string.fail_change_empty,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkParams() {

        String password= etPassword.getText().toString();
        String again= etPasswordAgain.getText().toString();
        String newPass= etPwdConfirm.getText().toString();
        boolean flag=false;
        if(!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(again)&&!TextUtils.isEmpty(again)){
            if(newPass.length()<6){
                Toast.makeText(UpdatePasswordActivity.this, R.string.fail_change_phone_min,
                        Toast.LENGTH_SHORT).show();
            }else if(newPass.length()>12){
                Toast.makeText(UpdatePasswordActivity.this, R.string.fail_change_phone_max,
                        Toast.LENGTH_SHORT).show();
            }else if(!newPass.equals(again)){
                Toast.makeText(UpdatePasswordActivity.this, R.string.fail_change_phone_confirm,
                        Toast.LENGTH_SHORT).show();
            }else{
                flag=true;
            }
        }

        return flag;
    }
}
