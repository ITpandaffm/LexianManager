/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.adapter.CollectionAdapter;
import com.example.administrator.lexianmarket.bean.user.CommodityCollection;
import com.example.administrator.lexianmarket.helper.Page;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.CollectionService;
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
public class CommodityCollectionActivity extends BaseActivity {


    private static final  int OBTAINED_COLLECTION=1;
    private static final int NO_MORE_DATA = 2;
    private TextView tvNoCollection;
    private PullToRefreshListView lvCollection;
    private LinearLayout llLoad;
    private Integer pageNo=1;
    private Integer totalPageNum;
    private CollectionAdapter adapter;
    private boolean isRefreshing;

    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case OBTAINED_COLLECTION:
                    ResultHelper<Page<CommodityCollection>> result=
                            (ResultHelper<Page<CommodityCollection>>) msg.obj;
                    if(result.getCode()== Constant.CODE_SUCCESS){
                        bindCollectionData(result);
                    }
                    setState();
                    break;
                case NO_MORE_DATA:
                    Toast.makeText(CommodityCollectionActivity.this, R.string.no_more,
                            Toast.LENGTH_SHORT).show();
                    lvCollection.onRefreshComplete();
                    break;
            }

            super.handleMessage(msg);
        }
    };

    private void setState() {
        llLoad.setVisibility(View.GONE);
        isRefreshing=false;
        lvCollection.onRefreshComplete();
    }

    private void bindCollectionData(ResultHelper<Page<CommodityCollection>> result) {
        Page<CommodityCollection> page=result.getData();
        pageNo=page.getPageNo();
        totalPageNum=page.getPageNums();
        List<CommodityCollection> ccs=page.getData();
        if(ccs!=null&&ccs.size()>0){
            tvNoCollection.setVisibility(View.GONE);
            lvCollection.setVisibility(View.VISIBLE);

            if(pageNo==1){



                adapter.setCollectionList(ccs);

            }else{
                adapter.addNewCollections(ccs);
            }
            adapter.notifyDataSetChanged();
        }else{
            tvNoCollection.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        setListener();

        setAdapter();

        initData();

    }

    private void setListener() {
        lvCollection.setOnItemClickListener(new OnGoCommodityDetailListener());
        lvCollection.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(!isRefreshing){
                    CollectionService.getCommodityCollection(1+"",handler);
                    isRefreshing=true;
                }

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(pageNo<totalPageNum){
                    if(!isRefreshing){
                        pageNo++;
                        CollectionService.getCommodityCollection(pageNo+"",handler);
                        isRefreshing=true;
                    }
                }else{
                    handler.sendEmptyMessage(NO_MORE_DATA);
                }
            }
        });

    }

    private void setAdapter() {
        adapter=new CollectionAdapter(this);
        lvCollection.setAdapter(adapter);
    }

    private void initData() {
        CollectionService.getCommodityCollection(pageNo+"",handler);
    }


    private void initViews() {
        setContentView(R.layout.activity_commodity_collection);
        ((TextView)findViewById(R.id.common_title_content)).setText(R.string.my_collection);
        tvNoCollection = (TextView) findViewById(R.id.tv_no_collection);
        lvCollection = (PullToRefreshListView) findViewById(R.id.lv_collection);
        lvCollection.setVisibility(View.GONE);
        //设置可上拉刷新和下拉刷新
        lvCollection.setMode(PullToRefreshBase.Mode.BOTH);
        //设置刷新时显示的文本
        ILoadingLayout startLayout = lvCollection.getLoadingLayoutProxy(true, false);
        startLayout.setPullLabel(getString(R.string.down_refresh));
        startLayout.setRefreshingLabel(getString(R.string.refreshing));
        startLayout.setReleaseLabel(getString(R.string.loosen_refresh));
        ILoadingLayout endLayout = lvCollection.getLoadingLayoutProxy(false, true);
        endLayout.setPullLabel(getString(R.string.up_refreshing));
        endLayout.setRefreshingLabel(getString(R.string.refreshing));
        endLayout.setReleaseLabel(getString(R.string.loosen_refresh));
        llLoad = (LinearLayout) findViewById(R.id.ll_load);
    }


    private class OnGoCommodityDetailListener implements
            android.widget.AdapterView.OnItemClickListener {
        /**
         * Callback method to be invoked when an item in this AdapterView has
         * been clicked.
         * <p>
         * Implementers can call getItemAtPosition(position) if they need
         * to access the data associated with the selected item.
         *
         * @param parent   The AdapterView where the click happened.
         * @param view     The view within the AdapterView that was clicked (this
         *                 will be a view provided by the adapter)
         * @param position The position of the view in the adapter.
         * @param id       The row id of the item that was clicked.
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CommodityCollection cc= (CommodityCollection) parent.getAdapter().getItem(position);
            Intent intent=new Intent(CommodityCollectionActivity.this,
                    CommodityDetailActivity.class);
            intent.putExtra("commodityNo",cc.getCommodityNo());
            intent.putExtra("storeNo",cc.getStoreNo());
            startActivity(intent);
        }
    }
}
