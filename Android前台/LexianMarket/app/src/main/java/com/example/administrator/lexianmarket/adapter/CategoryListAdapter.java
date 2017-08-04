/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
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
public class CategoryListAdapter extends BaseAdapter {

    private List<Category> categoryList;
    private Context context;
    private int currentPosition;

    public CategoryListAdapter(Context context, int currentPosition) {
        super();
        this.context = context;
        this.currentPosition = currentPosition;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryViewHolder viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_category_list,parent,false);
            viewHolder = new CategoryViewHolder();
            viewHolder.layout = (RelativeLayout) convertView
                    .findViewById(R.id.category_list_layout);
            viewHolder.title = (TextView) convertView.findViewById(R.id.category_list_title);
            viewHolder.itemView = convertView.findViewById(R.id.category_list_view);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (CategoryViewHolder) convertView.getTag();
        }
        Category item = categoryList.get(position);
        viewHolder.title.setText(item.getCategoryName()+"");
        if(position == currentPosition){
            viewHolder.layout.setBackgroundColor(context.getResources()
                    .getColor(R.color.bg_category_selected));
            viewHolder.itemView.setVisibility(View.VISIBLE);
            viewHolder.title.setTextColor(context.getResources().getColor(R.color.text_active));
        }else{
            viewHolder.layout.setBackgroundColor(context.getResources().getColor(R.color.bg_white));
            viewHolder.itemView.setVisibility(View.INVISIBLE);
            viewHolder.title.setTextColor(context.getResources().getColor(R.color.text_black));
        }
        return convertView;
    }


    public static class CategoryViewHolder{
        public RelativeLayout layout;
        public TextView title;
        public View itemView;
    }
}
