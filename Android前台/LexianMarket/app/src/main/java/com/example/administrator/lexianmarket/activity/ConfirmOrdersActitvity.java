/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.adapter.ConfirmListAdapter;
import com.example.administrator.lexianmarket.bean.user.Trolley;
import com.example.administrator.lexianmarket.helper.CashItem;
import com.example.administrator.lexianmarket.helper.CashParams;
import com.example.administrator.lexianmarket.view.ConfirmPayTypeDialog;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王晨昕
 * @version 1.0
 */
public class ConfirmOrdersActitvity extends BaseActivity {
    private List<Trolley> trolleys;
    private ConfirmListAdapter adapter;

    private ListView lv;
    private TextView tvTotal;
    private Button btnConfirmOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_orders_actitvity);

        initView();
        initData();
        setListener();
        initAdapter();
    }



    private void initAdapter() {
        adapter = new ConfirmListAdapter(ConfirmOrdersActitvity.this);
        adapter.setTrolleyList(trolleys);
        lv.setAdapter(adapter);
    }

    private void initData() {
        trolleys= (List<Trolley>) getIntent().getSerializableExtra("trolleys");
        java.text.DecimalFormat myFormat=new java.text.DecimalFormat("0.00");
        tvTotal.setText("￥"+myFormat.format(getTotalPrice()));
    }


    private void initView(){
        ((TextView)findViewById(R.id.common_title_content)).setText(R.string.confirm_orders);
        lv = (ListView) findViewById(R.id.lv_confirm);
        tvTotal = (TextView) findViewById(R.id.tv_confrim_total_price);
        btnConfirmOrders = (Button) findViewById(R.id.btn_confirm_orders);
    }
    private void setListener() {
        btnConfirmOrders.setOnClickListener(new OnConfirmOrdersClickListener());
    }

    private double getTotalPrice() {
        double totalPrice = 0.00;
        for(Trolley t: trolleys){
            totalPrice += t.getTotalPrice();
        }
        return totalPrice;
    }
    private class OnConfirmOrdersClickListener  implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            CashParams cashParams = new CashParams();
            List<CashItem> list = new ArrayList<CashItem>();
            for(Trolley item : trolleys ){
                CashItem cashItem = new CashItem();
                cashItem.setCommodityNo(item.getCommodityNo());
                cashItem.setStoreNo(item.getStoreNo());
                cashItem.setNumber(item.getAmont());
                list.add(cashItem);
            }
            cashParams.setCashItems(list);
            ConfirmPayTypeDialog confirmPayTypeDialog = new
                    ConfirmPayTypeDialog(ConfirmOrdersActitvity.this,cashParams,getTotalPrice());
            confirmPayTypeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            confirmPayTypeDialog.show();
        }
    }
}
