/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王晨昕
 * @version 1.0
 */
public class AnimationUtils {
    public static void showAnimation(View view, Animation.AnimationListener animationListener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setDuration(500);// 设置动画显示时间
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(animationListener);
    }
}
