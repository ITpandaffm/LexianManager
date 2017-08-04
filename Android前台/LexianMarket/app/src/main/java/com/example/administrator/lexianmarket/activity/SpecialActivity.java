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
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.adapter.SpecialAdapter;
import com.example.administrator.lexianmarket.bean.commodity.Special;
import com.example.administrator.lexianmarket.helper.Page;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.CommodityService;
import com.example.administrator.lexianmarket.utils.Constant;

import java.util.List;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class SpecialActivity extends BaseActivity {

    private List<Special> specialList;
    private static final int OBTAINED_SPECIALS = 1;
    private ListView lvSpecial;
    private SpecialAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case OBTAINED_SPECIALS:
                    ResultHelper<Page<Special>> result = (ResultHelper<Page<Special>>) msg.obj;
                    if (result.getCode() == Constant.CODE_SUCCESS) {
                        bindOrdersData(result);
                    }

                    break;


            }

            super.handleMessage(msg);
        }
    };

    private void bindOrdersData(ResultHelper<Page<Special>> result) {
        Page<Special> page = result.getData();
        specialList = page.getData();
        adapter.setSpecialList(specialList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);
        initViews();
        setAdapter();
        setListener();
        initData();
    }

    private void setListener() {
        lvSpecial.setOnItemClickListener(new OnGoSpecialCommodityClickListener());
    }

    private void initData() {
        CommodityService.getSpecials(1,handler);
    }
    private void setAdapter() {
        adapter = new SpecialAdapter(this);
        lvSpecial.setAdapter(adapter);
    }

    private void initViews() {
        ((TextView) findViewById(R.id.common_title_content)).setText(R.string.activity_lexian);
        lvSpecial = (ListView) findViewById(R.id.lv_special);
    }

    private class OnGoSpecialCommodityClickListener implements
            android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent =new Intent(SpecialActivity.this, SpecialCommodityActivity.class);
            intent.putExtra("id",specialList.get(position).getId());
            intent.putExtra("title",specialList.get(position).getName());
            startActivity(intent);
        }
    }
}
