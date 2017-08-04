/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.bean.commodity.Store;

import java.util.List;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class StoreAdapter extends BaseAdapter{

    private List<Store> stores;
    private Context context;

    public StoreAdapter(Context context) {
        this.context = context;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    @Override
    public int getCount() {

        if(stores==null){
            return 0;
        }
        return stores.size();
    }



    @Override
    public Object getItem(int position) {

        if(stores==null){
            return null;
        }
        return stores.get(position);
    }



    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StoreItemViewHolder storeItemViewHolder=null;

        if(convertView==null)
        {
            convertView=LayoutInflater.from(context).inflate(R.layout.item_store,null,false);
            storeItemViewHolder=new StoreItemViewHolder();
            storeItemViewHolder.tvStoreName= (TextView) convertView.findViewById(R.id.tv_store_name);
            convertView.setTag(storeItemViewHolder);
        }else{
            storeItemViewHolder= (StoreItemViewHolder) convertView.getTag();
        }

        storeItemViewHolder.tvStoreName.setText(stores.get(position).getStoreName());

        return convertView;
    }
    static class StoreItemViewHolder{
        public TextView tvStoreName;
    }
}
