/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.utils;

import android.content.Context;

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
public class BitmapUtilsHelper {
    private BitmapUtilsHelper(){

    }

    public static BitmapUtils getBitmapUtils(Context context){
        BitmapUtils bitmapUtils = new BitmapUtils(context);
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.app_icon);
        return bitmapUtils;
    }
}
