/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.activity.CommodityCollectionActivity;
import com.example.administrator.lexianmarket.activity.OrdersActivity;
import com.example.administrator.lexianmarket.activity.SpecialCommodityActivity;
import com.example.administrator.lexianmarket.adapter.CarouselAdapter;
import com.example.administrator.lexianmarket.helper.LoginHelper;

import java.util.Random;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王晨昕
 * @version 1.0
 */
public class HomeFragment extends Fragment {
    // 轮播图片相关的定义
    private ViewPager carouselContainer;
    private CarouselAdapter carouselAdapter;
    private int[] carouselImageIDs =
            {R.mipmap.carousel1, R.mipmap.carousel2, R.mipmap.carousel3,
                    R.mipmap.carousel4, R.mipmap.carousel5};
    private final int carouselCount = 5;
    private int currentCarouselIndex = 0;
    private LinearLayout carouselIndicatorsContainer;
    private final int MSG_CAROUSEL_SWITCH = 0x200;
    private boolean isStop = false;     // 判断轮播是否正在播放


    private LinearLayout llGoOrders;
    private LinearLayout llGoCollection;

    private ImageView ivGoOnSale;
    private ImageView ivGoNewSale;
    private ImageView ivGoRecommended;
    private ImageView ivGoSelected;

    private ScrollView scrollView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initView(rootView);

        setListener();

        initData();

        return rootView;
    }

    private void setListener() {

        View.OnClickListener listener = new OnGoOthersClickListener();
        llGoCollection.setOnClickListener(listener);
        llGoOrders.setOnClickListener(listener);

        View.OnClickListener specListener = new onGoSpecListener();
        ivGoOnSale.setOnClickListener(specListener);
        ivGoNewSale.setOnClickListener(specListener);
        ivGoRecommended.setOnClickListener(specListener);
        ivGoSelected.setOnClickListener(specListener);
        View.OnTouchListener viewPagerListener = new onViewPagerTouchListener();
        carouselContainer.setOnTouchListener(viewPagerListener);
    }
    private void initView(View view){
        carouselContainer = (ViewPager) view.findViewById(R.id.carousel_container);
        carouselIndicatorsContainer = (LinearLayout) view
                .findViewById(R.id.carousel_indicators_container);

        scrollView = (ScrollView) view.findViewById(R.id.home_category_scrollview);

        llGoOrders = (LinearLayout) view.findViewById(R.id.ll_go_orders);
        llGoCollection = (LinearLayout) view.findViewById(R.id.ll_go_collection);

        ivGoOnSale = (ImageView) view.findViewById(R.id.iv_go_on_sale);
        ivGoNewSale = (ImageView) view.findViewById(R.id.iv_go_new_sale);
        ivGoRecommended = (ImageView) view.findViewById(R.id.iv_go_recommended);
        ivGoSelected = (ImageView) view.findViewById(R.id.iv_go_selected);

    }

    private void initData(){
        // 初始化Carousel组件
        Random random = new Random(System.currentTimeMillis());
        currentCarouselIndex = random.nextInt(carouselCount);       // 选定从随机的序号开始轮播
        carouselAdapter = new CarouselAdapter(getActivity(), carouselImageIDs);
        carouselContainer.setAdapter(carouselAdapter);
        carouselContainer.addOnPageChangeListener(new OnImagePagerChangeListener());
        carouselContainer.setCurrentItem(currentCarouselIndex);
        toggleImageIndicator();
        carouselHandler.sendEmptyMessageDelayed(MSG_CAROUSEL_SWITCH, 2500);     // 发起轮播

        // 使scrollview定位到顶部
        scrollView.smoothScrollTo(0, 20);
    }

    private Handler carouselHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_CAROUSEL_SWITCH:
                    removeMessages(MSG_CAROUSEL_SWITCH);
                    sendEmptyMessageDelayed(MSG_CAROUSEL_SWITCH,1000);
                    toggleImagePager();
                    break;
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        carouselHandler.removeMessages(MSG_CAROUSEL_SWITCH);
        isStop = true;
        super.onPause();
    }

    @Override
    public void onResume() {
        if (isStop) {
            isStop = false;
            carouselHandler.sendEmptyMessageDelayed(MSG_CAROUSEL_SWITCH, 2500);
        }
        super.onResume();
    }

    private class OnImagePagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            carouselHandler.removeMessages(MSG_CAROUSEL_SWITCH);
            carouselHandler.sendEmptyMessageDelayed(MSG_CAROUSEL_SWITCH,2000);
            toggleImageIndicator();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public void toggleImageIndicator() {

        int index=carouselContainer.getCurrentItem();
        for(int i=0;i<carouselCount;i++){
            ImageView indicator = (ImageView)carouselIndicatorsContainer.getChildAt(i);
            if(i == index){
                indicator.setImageResource(R.drawable.carousel_indicator_active);
            } else{
                indicator.setImageResource(R.drawable.carousel_indicator_inactive);
            }
        }

    }

    public void toggleImagePager() {
        int next = (carouselContainer.getCurrentItem()+1)%carouselCount;
        carouselContainer.setCurrentItem(next);
        toggleImageIndicator();
    }

    private class OnGoOthersClickListener implements View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            if(v.getId()!=R.id.ll_go_category){
                if(LoginHelper.isLogin){
                    goOthersActivity(v.getId());
                }else{
                    Toast.makeText(getActivity(), R.string.login_first, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void goOthersActivity(int id) {
        Intent intent=null;
        switch (id){
            case R.id.ll_go_collection:
                intent=new Intent(getActivity(), CommodityCollectionActivity.class);
                break;
            case R.id.ll_go_orders:
                intent=new Intent(getActivity(), OrdersActivity.class);
                break;
        }
        if(intent!=null){
            startActivity(intent);
        }
    }

    private class onGoSpecListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            goSpecActivity(v.getId());
        }
    }

    private void goSpecActivity(int id) {
        int flag = 0;
        String title = "";
        switch (id) {
            case R.id.iv_go_on_sale:
                flag = 1;
                title = getString(R.string.specail_first);
                break;
            case R.id.iv_go_selected:
                flag = 2;
                title = getString(R.string.specail_two);
                break;
            case R.id.iv_go_recommended:
                flag = 3;
                title = getString(R.string.special_third);
                break;
            case R.id.iv_go_new_sale:
                flag = 4;
                title = getString(R.string.special_fourth);
                break;
        }
        Intent intent = new Intent(getActivity(), SpecialCommodityActivity.class);
        intent.putExtra("id", flag);
        intent.putExtra("title",title);
        startActivity(intent);
    }

    private class onViewPagerTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    carouselHandler.removeCallbacksAndMessages(null);
                    break;
                case MotionEvent.ACTION_UP:
                    carouselHandler.sendEmptyMessageDelayed(MSG_CAROUSEL_SWITCH, 2000);
                    break;
            }
            return false;
        }
    }
}
