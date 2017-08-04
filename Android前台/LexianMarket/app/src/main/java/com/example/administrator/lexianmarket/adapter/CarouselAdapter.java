/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.lexianmarket.R;
import com.lidroid.xutils.BitmapUtils;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王晨昕
 * @version 1.0
 */
public class CarouselAdapter extends PagerAdapter {
    private int[] imageIDs;
    private BitmapUtils utils;
    private LayoutInflater layoutInflater;

    public CarouselAdapter(Context context, int[] imageIDs) {
        this.layoutInflater = LayoutInflater.from(context);
        this.imageIDs = imageIDs;
    }

    @Override
    public int getCount() {
        return (imageIDs == null) ? 0 : imageIDs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.adapter_carousel_item, null, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.carousel_item);
        imageView.setImageResource(imageIDs[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
