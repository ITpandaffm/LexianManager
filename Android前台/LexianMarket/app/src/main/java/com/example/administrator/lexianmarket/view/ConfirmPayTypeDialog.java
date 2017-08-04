/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.bean.user.User;
import com.example.administrator.lexianmarket.bean.version.AppManager;
import com.example.administrator.lexianmarket.helper.CashParams;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.UserService;
import com.example.administrator.lexianmarket.utils.Constant;
import com.example.administrator.lexianmarket.view.PayDialog.PayDialog;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class ConfirmPayTypeDialog extends Dialog {
    private ImageView ivPayDialogBack;
    private TextView tvPayNumber;
    private TextView tvPayTotalPrice;
    private TextView tvPayType;
    private RelativeLayout rlPayType;
    private Button btnPayConfirm;
    private Context context;
    private CashParams cashParams;
    private double totalPrice;
    private static final int USER_PHONE=1;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case USER_PHONE:
                    ResultHelper<User> result= (ResultHelper<User>) msg.obj;

                    if(result.getCode()== Constant.CODE_SUCCESS){
                        bindData(result);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void bindData(ResultHelper<User> result) {
        User user = result.getData();
        tvPayNumber.setText(user.getPhone());
    }

    public ConfirmPayTypeDialog(Context context, CashParams cashParams,double totalPrice) {
        super(context);
        this.context=context;
        this.cashParams=cashParams;
        this.totalPrice=totalPrice;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay_type);
        getWindow().setGravity(Gravity.BOTTOM);

        WindowManager m =getWindow().getWindowManager();
        Display d =m.getDefaultDisplay();
        WindowManager.LayoutParams p =getWindow().getAttributes();
        p.width=d.getWidth();
        getWindow().setAttributes(p);
        initView();
        initData();
        setListener();
    }

    private void initView() {
        ivPayDialogBack = (ImageView) findViewById(R.id.iv_pay_dialog_back);
        tvPayNumber = (TextView) findViewById(R.id.tv_pay_number);
        tvPayTotalPrice = (TextView) findViewById(R.id.tv_pay_total_price);
        tvPayType = (TextView) findViewById(R.id.tv_pay_type);
        rlPayType = (RelativeLayout) findViewById(R.id.rl_pay_type);
        btnPayConfirm = (Button) findViewById(R.id.btn_pay_confirm);
    }
    private void initData() {
        UserService.getUser(handler);

        tvPayTotalPrice.setText(totalPrice+context.getString(R.string.yuan));
        tvPayType.setText(R.string.money_pay);

    }
    private void setListener() {
        btnPayConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PayDialog payDialog = new PayDialog(getContext(),cashParams);
                payDialog.show();
                dismiss();
            }
        });
        rlPayType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ivPayDialogBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
