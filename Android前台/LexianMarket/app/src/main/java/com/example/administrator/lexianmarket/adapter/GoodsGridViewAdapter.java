/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.activity.GoodsListActivity;
import com.example.administrator.lexianmarket.bean.commodity.Category;

import java.util.List;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王晨昕
 * @version 1.0
 */
public class GoodsGridViewAdapter extends BaseAdapter {

    public List<Category> categoryList;
    private Context context;

    public GoodsGridViewAdapter(Context context, List<Category> categoryList) {
        super();
        this.context =context;
        this.categoryList = categoryList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        if(categoryList!=null){
            return categoryList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(categoryList!=null){
            return categoryList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_category_goods_grid, viewGroup, false);
        }
        TextView title = (TextView)view.findViewById(R.id.category_goods_grid_title);
        Category item = categoryList.get(position);
        title.setText(item.getCategoryName()+"");

        return view;
    }

}
