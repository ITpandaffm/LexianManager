/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.adapter.GoodsListAdapter;
import com.example.administrator.lexianmarket.bean.commodity.CommodityStore;
import com.example.administrator.lexianmarket.helper.Page;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.CommodityService;
import com.example.administrator.lexianmarket.utils.Constant;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

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
public class GoodsListActivity extends BaseActivity {

    private static final int COMMODITY_LIST = 1;
    private static final int NO_MORE_DATA = 2;
    private Page<CommodityStore> page;
    private List<CommodityStore> commodityStoreList;
    private List<CommodityStore> newList;
    private int commodityID=0;
    private String storeNo;
    private GoodsListAdapter goodsListAdapter;

    private PullToRefreshGridView gv;
    private EditText et;
    private TextView btnFilter;
    private ImageView ivSale;
    private ImageView ivPrice;
    private LinearLayout llSale;
    private LinearLayout llPrice;

    private int saleCount;
    private int priceCount;

    private Integer pageNo = 1;
    private boolean isRefreshing;
    private Integer totalPageNum;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case COMMODITY_LIST:

                    ResultHelper<Page<CommodityStore>> result =
                            (ResultHelper<Page<CommodityStore>>) msg.obj;
                    if(result.getCode() == Constant.CODE_SUCCESS){
                        bindCommodityStore(result);
                        setState();
                    }
                    break;
                case NO_MORE_DATA:
                    Toast.makeText(GoodsListActivity.this, R.string.no_more,
                            Toast.LENGTH_SHORT).show();
                    gv.onRefreshComplete();
                    break;

            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);

        initView();
        initData();
        initAdapter();
        setListener();
    }

    private void setListener() {
        OnFilterClickListener filterClickListener = new OnFilterClickListener();

        gv.setOnItemClickListener(new OnGoCommodityDetailClickListener());
        et.addTextChangedListener(new OnCommoditySearchListener());
        llSale.setOnClickListener(filterClickListener);
        llPrice.setOnClickListener(filterClickListener);
        gv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                if(!isRefreshing){
                    if (commodityID !=0) {
                        CommodityService.getCommodityStore(1, commodityID, handler);
                        isRefreshing = true;
                    }else {
                        CommodityService.getCommodityStoreByStoreNo(pageNo,storeNo,handler);
                        isRefreshing = true;
                    }
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                if(pageNo<totalPageNum){
                    if(!isRefreshing){
                        if (commodityID !=0) {
                            pageNo++;
                            CommodityService.getCommodityStore(pageNo, commodityID, handler);
                            isRefreshing = true;
                        }else{
                            pageNo++;
                            CommodityService.getCommodityStoreByStoreNo(pageNo,storeNo,handler);
                            isRefreshing = true;
                        }
                    }
                }else{
                    handler.sendEmptyMessage(NO_MORE_DATA);
                }
            }
        });

    }
    private void setState() {
        isRefreshing=false;
        gv.onRefreshComplete();
    }

    public void initView() {
        ((TextView)findViewById(R.id.common_title_content)).setText(R.string.goods_list_title);
        gv = (PullToRefreshGridView) findViewById(R.id.goods_list_gridview);
        et = (EditText) findViewById(R.id.et_goods_search);
        ivSale = (ImageView) findViewById(R.id.iv_goods_list_sale);
        ivPrice = (ImageView) findViewById(R.id.iv_goods_list_price);
        btnFilter = (TextView) findViewById(R.id.btn_goods_filter);
        llSale = (LinearLayout) findViewById(R.id.ll_goods_list_sale);
        llPrice = (LinearLayout) findViewById(R.id.ll_goods_list_price);
        gv.setVisibility(View.GONE);
        //设置可上拉刷新和下拉刷新
        gv.setMode(PullToRefreshBase.Mode.BOTH);
        //设置刷新时显示的文本
        ILoadingLayout startLayout = gv.getLoadingLayoutProxy(true, false);
        startLayout.setPullLabel(getString(R.string.down_refresh));
        startLayout.setRefreshingLabel(getString(R.string.refreshing));
        startLayout.setReleaseLabel(getString(R.string.loosen_refresh));
        ILoadingLayout endLayout = gv.getLoadingLayoutProxy(false, true);
        endLayout.setPullLabel(getString(R.string.up_refreshing));
        endLayout.setRefreshingLabel(getString(R.string.refreshing));
        endLayout.setReleaseLabel(getString(R.string.loosen_refresh));
    }

    public void initAdapter() {
        goodsListAdapter = new GoodsListAdapter(GoodsListActivity.this);
        goodsListAdapter.setCommodityStoreList(commodityStoreList);
        gv.setAdapter(goodsListAdapter);
    }

    public void initData() {
        saleCount = 0;
        priceCount = 0;

        commodityID = getIntent().getIntExtra("commodityID", 0);
        storeNo=getIntent().getStringExtra("storeNo");
        if (commodityID !=0) {
            CommodityService.getCommodityStore(pageNo,commodityID, handler);
        }else{
            commodityID=0;
            CommodityService.getCommodityStoreByStoreNo(pageNo,storeNo,handler);
        }
    }

    public void bindCommodityStore(ResultHelper<Page<CommodityStore>> result) {
        page = result.getData();
        totalPageNum=page.getPageNums();
        commodityStoreList = page.getData();
        if(commodityStoreList!=null && commodityStoreList.size()>0){
            gv.setVisibility(View.VISIBLE);
            if (pageNo==1){
                goodsListAdapter.setCommodityStoreList(commodityStoreList);
            }else{
                goodsListAdapter.addNewCommodityStore(commodityStoreList);
            }
            goodsListAdapter.notifyDataSetChanged();
        }
    }

    private class OnGoCommodityDetailClickListener implements
            android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(GoodsListActivity.this,CommodityDetailActivity.class);
            CommodityStore commodityStore = (CommodityStore)goodsListAdapter.getItem(position);
            intent.putExtra("commodityNo", commodityStore.getCommodityNo());
            intent.putExtra("storeNo", commodityStore.getStoreNo());
            startActivity(intent);
        }
    }

    private class OnCommoditySearchListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            searchCommodities(s);
        }
    }

    private void searchCommodities(Editable s) {
        String title = s.toString();
        newList = new ArrayList<CommodityStore>();
        for(CommodityStore commodityStore: commodityStoreList){
            if(commodityStore.getCommodity().getName().indexOf(title) != -1){
                newList.add(commodityStore);
            }
        }
        if("".equals(title)){
            goodsListAdapter.setCommodityStoreList(commodityStoreList);
        }else if(newList.size() == 0){
            goodsListAdapter.setCommodityStoreList(newList);
            Toast.makeText(GoodsListActivity.this, R.string.no_commodity,
                    Toast.LENGTH_SHORT).show();
        }else{
            goodsListAdapter.setCommodityStoreList(newList);
        }
        goodsListAdapter.notifyDataSetChanged();
        newList = commodityStoreList;
    }

    private class OnFilterClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_goods_list_sale:
                    saleCount = changeImg(saleCount, ivSale);
                    break;
                case R.id.ll_goods_list_price:
                    priceCount = changeImg(priceCount, ivPrice);
                    break;
                case R.id.btn_goods_filter:
                    startFilting();
                    break;
                default:
                    break;
            }
        }
    }

    private int changeImg(int count, ImageView iv){
        count++;
        if(count%2 == 1){
            iv.setBackgroundResource(R.mipmap.good_list_select_down);
        }else{
            iv.setBackgroundResource(R.mipmap.good_list_select_up);
        }
        return count;
    }

    private void startFilting(){

    }

}
