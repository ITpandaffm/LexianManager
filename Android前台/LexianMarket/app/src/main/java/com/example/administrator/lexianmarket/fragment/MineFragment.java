/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.activity.AppManagerActivity;
import com.example.administrator.lexianmarket.activity.LoginActivity;
import com.example.administrator.lexianmarket.activity.OrdersActivity;
import com.example.administrator.lexianmarket.activity.SpecialActivity;
import com.example.administrator.lexianmarket.activity.UserInfoActivity;
import com.example.administrator.lexianmarket.activity.WalletActivity;
import com.example.administrator.lexianmarket.bean.user.User;
import com.example.administrator.lexianmarket.bean.user.Wallet;
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
 * @author 王晨昕
 * @version 1.0
 */
public class MineFragment extends Fragment {

    private static final int OBTAINED_USER=1;


    private LinearLayout userLayout;
    private TextView tvUsername;
    private TextView tvBalance;
    private RelativeLayout rlGoOrders;
    private LinearLayout llGoOrdersNopay;
    private LinearLayout llGoOrdersNoget;
    private LinearLayout llGoWallet;
    private RelativeLayout llGoSpecial;
    private RelativeLayout rlGoAbout;
    private ImageView ivUser;

    private Wallet wallet;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case OBTAINED_USER:

                    ResultHelper<User> result= (ResultHelper<User>) msg.obj;
                    if(result.getCode()== Constant.CODE_SUCCESS){
                        updateUserInfo(result);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onResume() {
        if(LoginHelper.isLogin){
            initData();
        }else{
            ivUser.setImageResource(R.mipmap.mine_default_user_icon);
            tvUsername.setText(R.string.login_first);
            tvBalance.setText("0.00");
        }
        super.onResume();
    }

    private void updateUserInfo(ResultHelper<User> result) {
        User user=result.getData();
        wallet=user.getWallet();
        tvUsername.setText(user.getUserName());
        if(wallet!=null){
            tvBalance.setText(wallet.getBalance()+"");
        }
        BitmapUtilsHelper.getBitmapUtils(getContext()).display(ivUser,user.getPortrait());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine, null);

        initViews(rootView);

        setListener();

        return rootView;
    }

    private void setListener() {
        userLayout.setOnClickListener(new OnUserLayoutClickListener());
        OnGoOrdersClickListener goOrdersClickListener=new OnGoOrdersClickListener();
        rlGoOrders.setOnClickListener(goOrdersClickListener);
        llGoOrdersNoget.setOnClickListener(goOrdersClickListener);
        llGoOrdersNopay.setOnClickListener(goOrdersClickListener);
        llGoWallet.setOnClickListener(goOrdersClickListener);

        llGoSpecial.setOnClickListener(new OnGoOthersClickListener());
        rlGoAbout.setOnClickListener(new OnGoOthersClickListener());
    }

    private void initData() {
        UserService.getUserWithWallet(handler);
    }

    private void initViews(View rootView) {
        userLayout = (LinearLayout) rootView.findViewById(R.id.mine_username_layout);
        tvBalance = (TextView) rootView.findViewById(R.id.tv_balance);
        tvUsername = (TextView) rootView.findViewById(R.id.tv_username);

        rlGoOrders = (RelativeLayout) rootView.findViewById(R.id.rl_go_orders);
        llGoOrdersNopay = (LinearLayout) rootView.findViewById(R.id.ll_go_orders_nopay);
        llGoOrdersNoget = (LinearLayout) rootView.findViewById(R.id.ll_go_orders_noget);
        rlGoAbout = (RelativeLayout) rootView.findViewById(R.id.rl_go_about);
        llGoWallet = (LinearLayout) rootView.findViewById(R.id.ll_go_wallet);
        llGoSpecial = (RelativeLayout) rootView.findViewById(R.id.ll_go_special);
        ivUser = (ImageView) rootView.findViewById(R.id.iv_user_icon);
    }

    private class OnGoOrdersClickListener implements View.OnClickListener{

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

            if(LoginHelper.isLogin){
                Intent intent=new Intent(getActivity(), OrdersActivity.class);
                switch (v.getId()){

                    case R.id.rl_go_orders:
                        startActivity(intent);
                        break;

                    case R.id.ll_go_orders_noget:
                        intent.putExtra("state",3);
                        startActivity(intent);
                        break;
                    case R.id.ll_go_orders_nopay:
                        intent.putExtra("state",1);
                        startActivity(intent);
                        break;
                    case R.id.ll_go_wallet:
                        Intent walletIntent=new Intent(getActivity(), WalletActivity.class);
                        walletIntent.putExtra("wallet",wallet);
                        startActivity(walletIntent);
                        break;
                }

            }else{
                Toast.makeText(getActivity(), R.string.login_first, Toast.LENGTH_SHORT).show();
            }



        }
    }

    private class OnUserLayoutClickListener implements View.OnClickListener{


        @Override
        public void onClick(View v) {

            if(!LoginHelper.isLogin){
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }else{
                Intent intent=new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
            }

        }
    }

    private class OnGoOthersClickListener implements View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.rl_go_about:
                    Intent  intent =new Intent(getActivity(), AppManagerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.ll_go_special:
                    Intent  intent2 =new Intent(getActivity(), SpecialActivity.class);
                    startActivity(intent2);
            }

        }
    }
}
