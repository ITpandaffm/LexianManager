/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.bean.commodity.CommodityStore;
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
public class GoodsListAdapter extends BaseAdapter {
    private List<CommodityStore> commodityStoreList;
    private Context context;
    private BitmapUtils bitmapUtils;


    public GoodsListAdapter(Context context) {
        super();
        this.context = context;
        this.bitmapUtils = BitmapUtilsHelper.getBitmapUtils(context);
    }

    public List<CommodityStore> getCommodityStoreList() {
        return commodityStoreList;
    }

    public void setCommodityStoreList(List<CommodityStore> commodityStoreList) {
        this.commodityStoreList = commodityStoreList;
    }
    public void addNewCommodityStore(List<CommodityStore> commodityStoreList){
        this.commodityStoreList.addAll(commodityStoreList);
    }

    @Override
    public int getCount() {

        if(commodityStoreList!=null){
            return commodityStoreList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {

        if(commodityStoreList!=null){
            return commodityStoreList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GoodsViewHolder viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_goods_list_grid,parent,false);
            viewHolder = new GoodsViewHolder();
            viewHolder.picture = (ImageView) convertView
                    .findViewById(R.id.goods_list_grid_imageview);
            viewHolder.title = (TextView) convertView.findViewById(R.id.goods_list_grid_title);
            viewHolder.price = (TextView) convertView.findViewById(R.id.goods_list_grid_price);

            convertView.setTag(viewHolder);
        }else{
            viewHolder= (GoodsListAdapter.GoodsViewHolder) convertView.getTag();
        }
        CommodityStore item = commodityStoreList.get(position);
        final GoodsViewHolder finalViewHolder = viewHolder;
        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            Boolean flag=true;
            @Override
            public void onClick(View v) {
                if (flag){
                    flag=false;
                    finalViewHolder.title.setEllipsize(null);
                    finalViewHolder.title.setLines(2);
                }else {
                    flag=true;
                    finalViewHolder.title.setEllipsize(TextUtils.TruncateAt.END);
                    finalViewHolder.title.setLines(1);
                }
            }
        });
        viewHolder.title.setText(item.getCommodity().getName()+"");
        viewHolder.price.setText("￥"+item.getRealPrice());
        String url = item.getCommodity().getPictureUrl();
        bitmapUtils.display(viewHolder.picture,url);
        return convertView;
    }


    public static class GoodsViewHolder{
        public ImageView picture;
        public TextView title;
        public TextView price;
    }
}
