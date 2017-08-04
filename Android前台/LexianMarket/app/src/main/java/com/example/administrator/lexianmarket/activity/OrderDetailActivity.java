/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.adapter.OrdersDetailAdapter;
import com.example.administrator.lexianmarket.bean.user.OrderItem;
import com.example.administrator.lexianmarket.bean.user.Orders;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.OrdersService;
import com.example.administrator.lexianmarket.utils.Constant;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class OrderDetailActivity extends BaseActivity {
    private static final int OBTAIN_ORDERS=1;
    private TextView mTvOrderNo;
    private TextView mTvStoreName;
    private TextView mTvUserName;
    private TextView mTvPhone;
    private ListView mLvOrdersDetail;
    private TextView mTvDeliveryType;
    private OrdersDetailAdapter adapter;
    private Orders orders;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case OBTAIN_ORDERS:

                    ResultHelper<Orders> result= (ResultHelper<Orders>) msg.obj;
                    if(result.getCode()== Constant.CODE_SUCCESS){
                        bindOrdersData(result);
                    }

                    break;
            }

            super.handleMessage(msg);
        }
    };

    private void bindOrdersData(ResultHelper<Orders> result) {

        orders=result.getData();
        mTvOrderNo.setText(orders.getOrderNo());
        mTvPhone.setText(orders.getUser().getPhone());
        mTvStoreName.setText(orders.getStore().getStoreName());
        mTvUserName.setText(orders.getUser().getUserName());
        mTvDeliveryType.setText(orders.getDeliveryType());
        adapter.setOrderItems(orders.getOrderItems());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        setListener();
        setAdapter();
        initData();
    }

    private void initData() {
        String id=getIntent().getIntExtra("id",-1)+"";
        if(!id.equals("-1")){
            OrdersService.getOrderDetail(id,handler);
        }
    }

    private void setListener() {
        mLvOrdersDetail.setOnItemClickListener(new OnGoCommodityClickListener());
    }

    private void setAdapter() {
        adapter=new OrdersDetailAdapter(this);
        mLvOrdersDetail.setAdapter(adapter);
    }

    private void initViews() {
        setContentView(R.layout.activity_order_detail);
        ((TextView)findViewById(R.id.common_title_content)).setText(R.string.order_detail);
        mTvOrderNo = (TextView) findViewById(R.id.tv_order_no);
        mTvStoreName = (TextView) findViewById(R.id.tv_store_name);
        mTvUserName = (TextView) findViewById(R.id.tv_userName);
        mTvPhone = (TextView) findViewById(R.id.tv_phone);
        mTvDeliveryType = (TextView) findViewById(R.id.tv_delivery_type);
        mLvOrdersDetail = (ListView) findViewById(R.id.lv_orders_detail);
    }

    private class OnGoCommodityClickListener implements
            android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            OrderItem oi= (OrderItem) parent.getAdapter().getItem(position);
            Intent intent=new Intent(OrderDetailActivity.this,CommodityDetailActivity.class);
            intent.putExtra("commodityNo",oi.getCommodity().getCommodityNo());
            intent.putExtra("storeNo",orders.getStore().getStoreNo());
            startActivity(intent);
        }
    }
}
