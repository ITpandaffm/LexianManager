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
import com.example.administrator.lexianmarket.bean.commodity.Special;

import java.util.List;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class SpecialAdapter extends BaseAdapter {
    private Context context;
    private List<Special> specialList;


    public SpecialAdapter (Context context){this.context=context;}

    public void setSpecialList(List<Special> specialList) {
        this.specialList = specialList;
    }
    public void addNewSpecialList(List<Special> specialList){
        this.specialList.addAll(specialList);
    }
    @Override
    public int getCount() {
        if (specialList ==null){
            return 0;
        }
        return  specialList.size();
    }

    @Override
    public Object getItem(int position) {
        if (specialList == null){
            return null;
        }
        return specialList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SpecialsViewHolder vw=null;

        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.item_special,null,false);

            vw=new SpecialsViewHolder();
            vw.tvSpecialName = (TextView) convertView.findViewById(R.id.tv_special_name);
            convertView.setTag(vw);

        }else{
            vw= (SpecialsViewHolder) convertView.getTag();
        }
        Special special=specialList.get(position);
        vw.tvSpecialName.setText(special.getName());


        return convertView;
    }

    private static class SpecialsViewHolder{
        public TextView tvSpecialName;
    }
}
