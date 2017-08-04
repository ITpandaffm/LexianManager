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
import com.example.administrator.lexianmarket.adapter.SpecialCommodityAdapter;
import com.example.administrator.lexianmarket.bean.commodity.Commodity;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.SpecialCommodityService;
import com.example.administrator.lexianmarket.utils.Constant;

import java.util.List;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王晨昕
 * @version 1.0
 */
public class SpecialCommodityActivity extends BaseActivity {

    private final static int SPEC_LIST = 1;

    private int id;
    private ListView lvSale;
    private SpecialCommodityAdapter specialCommodityAdapter;
    private List<Commodity> commodityList;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case SPEC_LIST:

                    ResultHelper<List<Commodity>> result = (ResultHelper<List<Commodity>>) msg.obj;
                    if(result.getCode() == Constant.CODE_SUCCESS){
                        bindSpecialCommodity(result);
                    }
                    break;

            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_commodity);

        initData();
        initView();
        initAdapter();
        setListener();

    }

    public void initData() {
        id = getIntent().getIntExtra("id", 0);
        String title= getIntent().getStringExtra("title");
        ((TextView)findViewById(R.id.common_title_content)).setText(title);
        SpecialCommodityService.getSpecialCommodity(id, handler);
    }

    public void initView() {
        lvSale = (ListView) findViewById(R.id.lv_sale);
    }

    public void initAdapter() {
        specialCommodityAdapter = new SpecialCommodityAdapter(SpecialCommodityActivity.this);
        specialCommodityAdapter.setCommodityList(commodityList);
        lvSale.setAdapter(specialCommodityAdapter);
    }

    public void bindSpecialCommodity(ResultHelper result){
        commodityList = (List<Commodity>) result.getData();
        if(commodityList!=null && commodityList.size()>0){
            specialCommodityAdapter.setCommodityList(commodityList);
            specialCommodityAdapter.notifyDataSetChanged();
        }
    }

    public void setListener() {
        lvSale.setOnItemClickListener(new OnGoCommodityDetail());
    }

    private class OnGoCommodityDetail implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SpecialCommodityActivity.this,
                    CommodityDetailActivity.class);
            String commodityNo = commodityList.get(position).getCommodityStores().getCommodityNo();
            String storeNo = commodityList.get(position).getCommodityStores().getStoreNo();
            intent.putExtra("commodityNo", commodityNo);
            intent.putExtra("storeNo", storeNo);
            startActivity(intent);
        }
    }


}
