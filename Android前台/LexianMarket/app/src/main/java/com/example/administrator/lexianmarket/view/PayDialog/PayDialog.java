/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.view.PayDialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.activity.OrdersActivity;
import com.example.administrator.lexianmarket.helper.CashParams;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.UserService;
import com.example.administrator.lexianmarket.utils.Constant;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class PayDialog extends BaseDialog{

    private PayPwdEditText payPwdEditText;
    private CashParams cashParams;
    private static final int CASH=1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case CASH:
                    ResultHelper<Object> result= (ResultHelper<Object>) msg.obj;
                    getData( result);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void getData(ResultHelper<Object> result) {
        if (result.getCode()==Constant.CODE_SUCCESS){
            Toast.makeText(getContext(), R.string.buy_success, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), OrdersActivity.class);
            context.startActivity(intent);
            dismiss();
        }else if (result.getCode()==Constant.CODE_NO_AMONT){
            Toast.makeText(getContext(), R.string.amount_not_enough, Toast.LENGTH_SHORT).show();
            dismiss();
        }else if(result.getCode()==Constant.CODE_BALANCE_NO_ENOUGH){
            Toast.makeText(getContext(), R.string.money_not_enough, Toast.LENGTH_SHORT).show();

            dismiss();
        }else if (result.getCode()==Constant.CODE_PAY_PASSWORD_ERROR){

            Toast.makeText(getContext(), R.string.password_error, Toast.LENGTH_SHORT).show();
            dismiss();
        }
        else {
            dismiss();
        }
    }

    public PayDialog(Context context) {

        super(context);
    }
    public PayDialog(Context context,CashParams cashParams) {
        super(context);
        this.cashParams=cashParams;
        this.context=context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_dialog_lyaout);

        payPwdEditText = (PayPwdEditText) findViewById(R.id.ppet);

        payPwdEditText.initStyle(R.drawable.edit_num_bg_red,
                6,1f, R.color.bg_black, R.color.bg_black, 20);
        payPwdEditText.setOnTextFinishListener(new PayPwdEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {//密码输入完后的回调
                cashParams.setPassword(str);
                UserService.cash(cashParams,handler);
            }

        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                payPwdEditText.setFocus();
            }
        }, 1);

    }

}
