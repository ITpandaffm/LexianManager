/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.activity.GoodsListActivity;
import com.example.administrator.lexianmarket.bean.user.Trolley;
import com.example.administrator.lexianmarket.utils.BitmapUtilsHelper;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王晨昕
 * @version 1.0
 */
public class ConfirmListAdapter extends BaseAdapter {
    private List<Trolley> trolleyList;
    private Context context;
    private BitmapUtils bitmapUtils;

    public ConfirmListAdapter(Context context){
        super();
        this.context = context;
        this.bitmapUtils = BitmapUtilsHelper.getBitmapUtils(context);
    }

    public List<Trolley> getTrolleyList() {
        return trolleyList;
    }

    public void setTrolleyList(List<Trolley> trolleyList) {
        this.trolleyList = trolleyList;
    }

    @Override
    public int getCount() {
        if(trolleyList!=null){
            return trolleyList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(trolleyList!=null){
            return trolleyList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConfirmOrderViewHolder viewHolder = null;
        java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.00");

        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_confirm_list,parent,false);
            viewHolder = new ConfirmOrderViewHolder();
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv_confirm_img);
            viewHolder.tvStore = (TextView) convertView.findViewById(R.id.tv_confirm_store);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_confirm_title);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_confirm_price);
            viewHolder.tvConfirmPositionPrice = (TextView) convertView
                    .findViewById(R.id.tv_confirm_position_price);
            viewHolder.llConfirmStore =(LinearLayout)convertView
                    .findViewById(R.id.ll_confirm_store);
            viewHolder.tvConfirmAmount = (TextView) convertView
                    .findViewById(R.id.tv_confirm_amount);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ConfirmOrderViewHolder) convertView.getTag();
        }
        Trolley item = trolleyList.get(position);
        viewHolder.tvStore.setText(item.getStore().getStoreName());
        viewHolder.tvTitle.setText(item.getCommodity().getName());
        viewHolder.tvPrice.setText("￥"+myformat.format(item.getListPrice()));
        viewHolder.tvConfirmPositionPrice
                .setText(context.getString(R.string.all)+item.getAmont()+context.getString(R.string.commodity_jian)+"  "+context.getString(R.string.confirm_total_item)+
                        myformat.format(item.getTotalPrice()));
        viewHolder.tvConfirmAmount.setText("×"+item.getAmont()+"");
        bitmapUtils.display(viewHolder.iv,item.getCommodity().getPictureUrl());
        bindData(viewHolder.llConfirmStore,position);
        setListener(viewHolder);
        return convertView;
    }

    public static class ConfirmOrderViewHolder{
        public ImageView iv;
        public TextView tvStore;
        public TextView tvTitle;
        public TextView tvPrice;
        private LinearLayout llConfirmStore;
        private TextView tvConfirmAmount;
        private TextView tvConfirmPositionPrice;
    }
    private void setListener(ConfirmOrderViewHolder vw){
        View.OnClickListener listener=new ConfirmListAdapter.OnClickListener();
        vw.llConfirmStore.setOnClickListener(listener);
    }
    private void bindData(View view, int position) {
        view.setTag(position);
    }
    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int position= (int) v.getTag();
            Trolley trolley =trolleyList.get(position);
            switch (v.getId()){
                case R.id.ll_confirm_store:
                    Intent intent=new Intent(context, GoodsListActivity.class);
                    intent.putExtra("storeNo",trolley.getStoreNo());
                    context.startActivity(intent);
                    break;
            }
        }
    }
}
