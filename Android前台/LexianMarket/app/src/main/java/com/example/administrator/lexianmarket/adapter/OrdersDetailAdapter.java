/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.bean.user.OrderItem;
import com.example.administrator.lexianmarket.utils.BitmapUtilsHelper;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class OrdersDetailAdapter extends BaseAdapter {

    private Context context;
    private List<OrderItem> orderItems;
    private BitmapUtils bitmapUtils;
    public OrdersDetailAdapter(Context context) {
        this.context = context;
        this.bitmapUtils = BitmapUtilsHelper.getBitmapUtils(context);
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public int getCount() {
        if(orderItems==null){
            return 0;
        }

        return orderItems.size();
    }

    @Override
    public Object getItem(int position) {

        if(orderItems==null){
            return null;
        }

        return orderItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderDetailViewHolder vw=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context)
                    .inflate(R.layout.item_order_detail,null,false);
            vw=new OrderDetailViewHolder();
            vw.ivCover = (ImageView) convertView.findViewById(R.id.iv_cover);
            vw.tvCommodityName = (TextView) convertView.findViewById(R.id.tv_commodity_name);
            vw.tvTotalAmount = (TextView) convertView.findViewById(R.id.tv_total_amount);
            vw.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            vw.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
            convertView.setTag(vw);
        }else{
            vw= (OrderDetailViewHolder) convertView.getTag();
        }
        OrderItem oi=orderItems.get(position);
        bitmapUtils.display(vw.ivCover,oi.getCommodity().getPictureUrl());
        vw.tvCommodityName.setText(oi.getCommodity().getName());
        vw.tvNum.setText(context.getString(R.string.number)+oi.getAmount());
        vw.tvPrice.setText(context.getString(R.string.real_price)+oi.getListPrice());
        vw.tvTotalAmount.setText(context.getString(R.string.total_price)+oi.getTotalPrice());
        return convertView;
    }

    private class OrderDetailViewHolder{
        public TextView tvTotalAmount;
        public TextView tvPrice;
        public TextView tvNum;
        public TextView tvCommodityName;
        public ImageView ivCover;
    }


}
