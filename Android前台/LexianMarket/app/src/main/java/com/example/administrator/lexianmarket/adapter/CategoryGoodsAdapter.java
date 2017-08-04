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
import com.example.administrator.lexianmarket.view.GoodsGridView;

import java.util.List;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王晨昕
 * @version 1.0
 */
public class CategoryGoodsAdapter extends BaseAdapter{

    private List<Category> categoryList;
    private Context context;

    public CategoryGoodsAdapter(Context context) {
        super();
        this.context = context;
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

        CategoryGoodsViewHolder viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_category_goods,parent,false);
            viewHolder = new CategoryGoodsViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.category_goods_title);
            viewHolder.gridView = (GoodsGridView) convertView
                    .findViewById(R.id.categories_goods_gridview);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (CategoryGoodsViewHolder) convertView.getTag();
        }
        Category item = categoryList.get(position);
        viewHolder.title.setText(item.getCategoryName()+"");
        viewHolder.gridView.setAdapter(new GoodsGridViewAdapter(context, item.getThirdCategory()));
        viewHolder.gridView
                .setOnItemClickListener(new CategoryGridView_ItemClickListener(position));

        return convertView;
    }

    public static class CategoryGoodsViewHolder{
        public TextView title;
        public GoodsGridView gridView;

    }

    public class CategoryGridView_ItemClickListener implements AdapterView.OnItemClickListener {

        private int parentPosition;

        public CategoryGridView_ItemClickListener(int parentPosition) {
            super();
            this.parentPosition = parentPosition;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int commodityID = categoryList.get(parentPosition)
                    .getThirdCategory().get(position).getId();
            Intent intent = new Intent(context, GoodsListActivity.class);
            intent.putExtra("commodityID", commodityID);
            context.startActivity(intent);
        }
    }
}
