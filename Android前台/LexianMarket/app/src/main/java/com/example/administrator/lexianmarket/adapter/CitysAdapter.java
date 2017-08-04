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
import com.example.administrator.lexianmarket.bean.city.Citys;

import java.util.List;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class CitysAdapter extends BaseAdapter{

    private List<Citys> cities;

    private Context context;

    public void setCities(List<Citys> cities) {
        this.cities = cities;
    }

    public CitysAdapter(Context context) {
        this.context = context;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        if(cities ==null){
            return 0;
        }

        return cities.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {

        if(cities ==null){
            return null;
        }

        return cities.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CityItemViewHolder cityItemViewHolder=null;

        if(convertView==null)
        {
            convertView=LayoutInflater.from(context).inflate(R.layout.item_citys,null,false);
            cityItemViewHolder=new CityItemViewHolder();
            cityItemViewHolder.tvCityName= (TextView) convertView.findViewById(R.id.tv_citys_name);
            convertView.setTag(cityItemViewHolder);
        }else{
            cityItemViewHolder= (CityItemViewHolder) convertView.getTag();
        }

        cityItemViewHolder.tvCityName.setText(cities.get(position).getCity());

        return convertView;

    }
    static class CityItemViewHolder{
        public TextView tvCityName;
    }
}
