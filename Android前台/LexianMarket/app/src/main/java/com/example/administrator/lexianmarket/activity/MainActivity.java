/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.bean.user.User;
import com.example.administrator.lexianmarket.fragment.CartFragment;
import com.example.administrator.lexianmarket.fragment.CategoryFragment;
import com.example.administrator.lexianmarket.fragment.HomeFragment;
import com.example.administrator.lexianmarket.fragment.MineFragment;
import com.example.administrator.lexianmarket.fragment.StoreFragment;
import com.example.administrator.lexianmarket.helper.LoginHelper;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.UserService;
import com.example.administrator.lexianmarket.utils.Constant;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王晨昕
 * @version 1.0
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Fragment[] fragments;               // 记录所有Fragments对象的数组
    private Fragment currentFragment;
    private static int currentFragmentIndex;

    private LinearLayout fragmentIndicatorsContainer;   // Fragments菜单项容器
    private LinearLayout[] fragmentIndicators;  // 记录所有Fragments菜单项对象的数组

    // 定义Fragments菜单在激活和非激活状态下的图片
    private final int[] ICON_SELECT_RESID = {R.mipmap.m_home_active,
            R.mipmap.m_category_active, R.mipmap.m_location_active, R.mipmap.m_cart_active,
            R.mipmap.m_mine_active};
    private final int[] ICON_UNSELECT_RESID = {R.mipmap.m_home_inactive,
            R.mipmap.m_category_inactive, R.mipmap.m_location_inactive,
            R.mipmap.m_cart_inactive, R.mipmap.m_mine_inactive};
    // 定义Fragments菜单文字在激活和非激活状态下的颜色
    private int colorUnSelect;
    private int colorSelect;
    private static final int LOGIN_RESPONSE=1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case LOGIN_RESPONSE:

                    ResultHelper<User> result= (ResultHelper<User>) msg.obj;
                    if(result.getCode()== Constant.CODE_SUCCESS){
                        LoginHelper.isLogin=true;
                    }else{
                        LoginHelper.isLogin=false;
                    }
                    break;

            }


            super.handleMessage(msg);
        }
    };

    long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    public void initView() {
        fragmentIndicatorsContainer =
                (LinearLayout)findViewById(R.id.fragment_indicators_container);
        fragmentIndicators = new LinearLayout[fragmentIndicatorsContainer.getChildCount()];
        for (int i = 0; i < fragmentIndicators.length; i++) {
            fragmentIndicators[i] = (LinearLayout)fragmentIndicatorsContainer.getChildAt(i);
            fragmentIndicators[i].setTag(Integer.valueOf(i));
            fragmentIndicators[i].setOnClickListener(this);
        }

        colorUnSelect = getResources().getColor(R.color.text_inactive);
        colorSelect = getResources().getColor(R.color.text_active);
    }

    public void initData(){
        fragments = new Fragment[5];
        fragments[0] = new HomeFragment();
        fragments[1] = new CategoryFragment();
        fragments[2] = new StoreFragment();
        fragments[3] = new CartFragment();
        fragments[4] = new MineFragment();

        if (LoginHelper.isLogin ==false){
            SharedPreferences sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            if (sp.getBoolean("AUTO_ISCHECK",false)){
                String phone= sp.getString("USER_NAME","");
                String password= sp.getString("PASSWORD","");
                UserService.signIn(phone,password,handler);
            }
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, fragments[0]).commit();
        ((ImageView) fragmentIndicators[0].getChildAt(0)).setImageResource(ICON_SELECT_RESID[0]);
        ((TextView) fragmentIndicators[0].getChildAt(1)).setTextColor(colorSelect);

        currentFragmentIndex = 0;
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        if(R.id.ll_go_category==view.getId()){
            updateSelectedFragment(1);
            return ;
        }else if(R.id.ll_go_cart==id){
            updateSelectedFragment(3);
            return ;
        }
        int tag = (int) view.getTag();
        if (tag != currentFragmentIndex) {
            updateSelectedFragment(tag);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) >= 2000) {
                Toast.makeText(this, R.string.app_exit_indicator, Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 切换不同的Fragment后，要刷新菜单图片和文字状态
    private void updateSelectedFragment(int position) {
        ((ImageView) fragmentIndicators[currentFragmentIndex]
                .getChildAt(0)).setImageResource(ICON_UNSELECT_RESID[currentFragmentIndex]);
        ((TextView) fragmentIndicators[currentFragmentIndex].getChildAt(1))
                .setTextColor(colorUnSelect);

        ((ImageView) fragmentIndicators[position].getChildAt(0))
                .setImageResource(ICON_SELECT_RESID[position]);
        ((TextView) fragmentIndicators[position].getChildAt(1)).setTextColor(colorSelect);

        switchFragment(fragments[currentFragmentIndex], fragments[position], position);
    }

    /**
     * 切换fragment
     * @param from     当前Fragment
     * @param to       目标Fragment
     * @param position 当前的位置
     */
    private void switchFragment(Fragment from, Fragment to, int position) {
        if (currentFragment != to) {
            currentFragment = to;

            if (!to.isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(from)
                        .add(R.id.fragment_content, to).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(from).show(to).commit();
            }
            currentFragmentIndex = position;
        }
    }


}
