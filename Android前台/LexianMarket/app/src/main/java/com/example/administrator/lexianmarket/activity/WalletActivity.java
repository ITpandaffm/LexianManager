/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.bean.user.Wallet;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王子龙
 * @version 1.0
 */
public class WalletActivity extends BaseActivity {
    private Wallet wallet;
    private TextView tvBalance;
    private TextView commonTitleRightText;
    private TextView commonTitleContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        initViews();
        initData();
        setListener();
    }

    private void setListener() {
        commonTitleRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        Intent intent=getIntent();
        wallet= (Wallet) intent.getSerializableExtra("wallet");
        if(wallet!=null){
            tvBalance.setText(getString(R.string.money)+wallet.getBalance());
        }else{
            tvBalance.setText(R.string.zero_money);
        }

    }

    private void initViews() {
        tvBalance= (TextView) findViewById(R.id.balance);
        commonTitleRightText = (TextView) findViewById(R.id.common_title_right_text);
        commonTitleContent =(TextView) findViewById(R.id.common_title_content);
        commonTitleRightText.setText(R.string.first_page);
        commonTitleContent.setText(R.string.my_wallet);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
    }


}
