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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.adapter.OrdersAdapter;
import com.example.administrator.lexianmarket.bean.user.Orders;
import com.example.administrator.lexianmarket.fragment.MineFragment;
import com.example.administrator.lexianmarket.helper.Page;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.OrdersService;
import com.example.administrator.lexianmarket.utils.Constant;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class OrdersActivity extends BaseActivity {

    private Integer pageNo = 1;
    private static final int OBTAINED_ORDERS = 1;
    private static final int NO_MORE_DATA = 2;
    private PullToRefreshListView lvOrders;
    private LinearLayout llLoad;
    private OrdersAdapter adapter;
    private String state;
    private TextView tvNoOrders;
    private Integer totalPageNum;
    private boolean isRefreshing;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case OBTAINED_ORDERS:
                    ResultHelper<Page<Orders>> result = (ResultHelper<Page<Orders>>) msg.obj;

                    if (result.getCode() == Constant.CODE_SUCCESS) {
                        bindOrdersData(result);
                    }
                    setState();

                    break;
                case NO_MORE_DATA:
                    Toast.makeText(OrdersActivity.this,R.string.no_more, Toast.LENGTH_SHORT).show();
                    lvOrders.onRefreshComplete();
                    break;

            }

            super.handleMessage(msg);
        }
    };

    private void setState() {
        llLoad.setVisibility(View.GONE);
        isRefreshing=false;
        lvOrders.onRefreshComplete();
    }

    private void bindOrdersData(ResultHelper<Page<Orders>> result) {
        Page<Orders> page = result.getData();
        pageNo = page.getPageNo();
        totalPageNum = page.getPageNums();
        List<Orders> orderss = page.getData();
        if (orderss != null && orderss.size() > 0) {

            tvNoOrders.setVisibility(View.GONE);
            lvOrders.setVisibility(View.VISIBLE);

            if(pageNo==1){
                adapter.setOrdersList(orderss);
            }else{
                adapter.addNewOrders(orderss);
            }
            adapter.notifyDataSetChanged();
        } else {
            tvNoOrders.setVisibility(View.VISIBLE);
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        setAdapter();
        setListener();
        initData();
    }

    private void setListener() {
        lvOrders.setOnItemClickListener(new OnGoDetailClickListener());
        ((TextView) findViewById(R.id.common_title_right_text)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrdersActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        lvOrders.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(!isRefreshing){
                    OrdersService.getOrders(1+"",state,handler);
                    isRefreshing=true;
                }

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(pageNo<totalPageNum){
                    if(!isRefreshing){
                        pageNo++;
                        OrdersService.getOrders(pageNo+"",state,handler);
                        isRefreshing=true;
                    }
                }else{
                    handler.sendEmptyMessage(NO_MORE_DATA);
                }
            }
        });
    }


    private void initData() {

        Intent intent = getIntent();
        state = intent.getIntExtra("state", -1)+"";
        if (state.equals("-1")) {
            state=null;
            OrdersService.getOrders(pageNo + "", state, handler);

        } else {
            OrdersService.getOrders(pageNo + "", state + "", handler);
        }

    }

    private void setAdapter() {
        adapter = new OrdersAdapter(this);
        lvOrders.setAdapter(adapter);
    }

    private void initViews() {
        setContentView(R.layout.activity_orders);
        ((TextView) findViewById(R.id.common_title_content)).setText(R.string.my_order);
        ((TextView) findViewById(R.id.common_title_right_text)).setText(R.string.first_page);
        llLoad = (LinearLayout) findViewById(R.id.ll_load);

        lvOrders = (PullToRefreshListView) findViewById(R.id.lv_orders);
        lvOrders.setVisibility(View.GONE);
        //设置可上拉刷新和下拉刷新
        lvOrders.setMode(PullToRefreshBase.Mode.BOTH);
        //设置刷新时显示的文本
        ILoadingLayout startLayout = lvOrders.getLoadingLayoutProxy(true, false);
        startLayout.setPullLabel(getString(R.string.down_refresh));
        startLayout.setRefreshingLabel(getString(R.string.refreshing));
        startLayout.setReleaseLabel(getString(R.string.loosen_refresh));
        ILoadingLayout endLayout = lvOrders.getLoadingLayoutProxy(false, true);
        endLayout.setPullLabel(getString(R.string.up_refreshing));
        endLayout.setRefreshingLabel(getString(R.string.refreshing));
        endLayout.setReleaseLabel(getString(R.string.loosen_refresh));
        tvNoOrders = (TextView) findViewById(R.id.tv_no_orders);
    }


    private class OnGoDetailClickListener implements
            android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Orders orders = (Orders) parent.getAdapter().getItem(position);

            Intent intent = new Intent(OrdersActivity.this, OrderDetailActivity.class);
            intent.putExtra("id", orders.getId());
            startActivity(intent);
        }
    }


}
