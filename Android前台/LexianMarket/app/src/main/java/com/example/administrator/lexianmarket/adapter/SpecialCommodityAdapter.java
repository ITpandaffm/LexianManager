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
import com.example.administrator.lexianmarket.bean.commodity.Commodity;
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
public class SpecialCommodityAdapter extends BaseAdapter {

    private List<Commodity> commodityList;
    private Context context;
    private BitmapUtils bitmapUtils;

    public SpecialCommodityAdapter(Context context){
        super();
        this.context = context;
        this.bitmapUtils = BitmapUtilsHelper.getBitmapUtils(context);

    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    @Override
    public int getCount() {
        if(commodityList != null){
            return commodityList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(commodityList != null){
            return commodityList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpecialCommodityViewHolder viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_special_commodity,parent,false);
            viewHolder = new SpecialCommodityViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.iv_sale_img);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_sale_title);
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_sale_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (SpecialCommodityViewHolder) convertView.getTag();
        }
        Commodity item = commodityList.get(position);
        viewHolder.title.setText(item.getName()+"");
        viewHolder.price.setText("￥"+item.getCommodityStores().getRealPrice());
        bitmapUtils.display(viewHolder.img, item.getPictureUrl());

        return convertView;
    }

    public static class SpecialCommodityViewHolder{
        public ImageView img;
        public TextView title;
        public TextView price;
    }
}
