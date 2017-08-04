/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.activity.CommodityDetailActivity;
import com.example.administrator.lexianmarket.activity.ConfirmOrdersActitvity;
import com.example.administrator.lexianmarket.adapter.CartAdapter;
import com.example.administrator.lexianmarket.bean.user.Trolley;
import com.example.administrator.lexianmarket.helper.LoginHelper;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.CartService;
import com.example.administrator.lexianmarket.utils.Constant;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class CartFragment extends Fragment {

    private static final int OBTAIN_CART=1;
    private static final String TAG="CartFragment";
    private TextView mTvNoCart;
    private TextView mTvNoLogin;
    private PullToRefreshListView mLvCart;
    private Button mBtnClearCart;
    private Button mBtnCartCash;
    private Button mBtnDeleteCart;
    private RelativeLayout rlBottom;
    private Integer pageNo=1;
    private Integer totalPageNum=1;
    private LinearLayout llLoad;
    private boolean isRefreshing;
    private static final int NO_MORE_DATA = 2;
    private CartAdapter adapter;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case OBTAIN_CART:

                    ResultHelper<List<Trolley>> result= (ResultHelper<List<Trolley>>) msg.obj;
                    if(result.getCode()== Constant.CODE_SUCCESS){
                        bindCartData(result);
                    }
                    setState();
                    break;
                case NO_MORE_DATA:
                    Toast.makeText(getContext(),R.string.no_more, Toast.LENGTH_SHORT).show();
                    mLvCart.onRefreshComplete();
                    break;
            }

            super.handleMessage(msg);
        }
    };
    private void setState() {
        llLoad.setVisibility(View.GONE);
        isRefreshing=false;
        mLvCart.onRefreshComplete();
    }
    private void bindCartData(ResultHelper<List<Trolley>> result) {

        List<Trolley> trolleys=result.getData();
        if(trolleys!=null&&trolleys.size()>0){

            if(pageNo==1){

                ArrayList<Trolley> selectedTrolleys=new ArrayList<>();

                if(adapter.getCount()>0){
                    for(Trolley t:adapter.getTrolleys()){
                        if(t.isSelected()){
                            selectedTrolleys.add(t);
                        }
                    }
                }

                for(Trolley t:trolleys){
                    if(selectedTrolleys.contains(t)){
                        t.setSelected(true);
                    }
                }

                adapter.setTrolleys(trolleys);
            }else{
                adapter.addNewTrolleys(trolleys);
            }


            adapter.notifyDataSetChanged();

            mTvNoLogin.setVisibility(View.GONE);
            mTvNoCart.setVisibility(View.GONE);
            mLvCart.setVisibility(View.VISIBLE);
            rlBottom.setVisibility(View.VISIBLE);
        }else{
            mTvNoLogin.setVisibility(View.GONE);
            mTvNoCart.setVisibility(View.VISIBLE);
            mLvCart.setVisibility(View.GONE);
            rlBottom.setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_cart, container, false);

        initView(rootView);

        setAdapter();

        setListener();

        initData();

        return rootView;
    }

    private void setListener() {

        View.OnClickListener listener=new OnTrolleyClickListener();
        mBtnCartCash.setOnClickListener(listener);
        mBtnClearCart.setOnClickListener(listener);
        mBtnDeleteCart.setOnClickListener(listener);
        mLvCart.setOnItemClickListener(new OnGoCommodityClickListener());

        mLvCart.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(!isRefreshing){
                    CartService.getTrolleys(pageNo+"",handler);
                    isRefreshing=true;
                }

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(pageNo<totalPageNum){
                    if(!isRefreshing){
                        pageNo++;
                        CartService.getTrolleys(pageNo+"",handler);
                        isRefreshing=true;
                    }
                }else{
                    handler.sendEmptyMessage(NO_MORE_DATA);
                }
            }
        });
    }

    private void setAdapter() {
        adapter=new CartAdapter(getActivity());
        mLvCart.setAdapter(adapter);
    }

    @Override
    public void onResume() {

        llLoad.setVisibility(View.VISIBLE);
        initData();
        super.onResume();
    }

    private void initData() {

        if(LoginHelper.isLogin){
            CartService.getTrolleys(pageNo+"",handler);
        }else{
            llLoad.setVisibility(View.GONE);
            mTvNoLogin.setVisibility(View.VISIBLE);
            mTvNoCart.setVisibility(View.GONE);
            mLvCart.setVisibility(View.GONE);
            rlBottom.setVisibility(View.GONE);
        }

    }

    private void initView(View rootView) {
        mTvNoCart = (TextView) rootView.findViewById(R.id.tv_no_cart);
        mLvCart = (PullToRefreshListView) rootView.findViewById(R.id.lv_cart);
        mLvCart.setVisibility(View.GONE);
        //设置可上拉刷新和下拉刷新
        mLvCart.setMode(PullToRefreshBase.Mode.BOTH);
        //设置刷新时显示的文本
        ILoadingLayout startLayout = mLvCart.getLoadingLayoutProxy(true, false);
        startLayout.setPullLabel(getString(R.string.down_refresh));
        startLayout.setRefreshingLabel(getString(R.string.refreshing));
        startLayout.setReleaseLabel(getString(R.string.loosen_refresh));
        ILoadingLayout endLayout = mLvCart.getLoadingLayoutProxy(false, true);
        endLayout.setPullLabel(getString(R.string.up_refreshing));
        endLayout.setRefreshingLabel(getString(R.string.refreshing));
        endLayout.setReleaseLabel(getString(R.string.loosen_refresh));
        mBtnClearCart = (Button) rootView.findViewById(R.id.btn_clear_cart);
        mBtnCartCash = (Button) rootView.findViewById(R.id.btn_cart_cash);
        mBtnDeleteCart = (Button) rootView.findViewById(R.id.btn_delete_cart);
        rlBottom = (RelativeLayout) rootView.findViewById(R.id.rl_bottom);
        mTvNoLogin = (TextView) rootView.findViewById(R.id.tv_no_login);
        llLoad = (LinearLayout) rootView.findViewById(R.id.ll_load);
    }

    private class OnTrolleyClickListener implements View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_cart_cash:
                    goCash();
                    break;
                case R.id.btn_clear_cart:
                    clearCart();
                    break;
                case R.id.btn_delete_cart:
                    deleteSelectedItems();
                    break;
            }
        }
    }

    private void goCash() {
        Intent intent=new Intent(getActivity(), ConfirmOrdersActitvity.class);
        ArrayList<Trolley> selectedTrolleys=new ArrayList<>();

        for(Trolley t:adapter.getTrolleys()){
            if(t.isSelected()){
                selectedTrolleys.add(t);
            }
        }
        if(selectedTrolleys.size()==0){
            Toast.makeText(getContext(), R.string.select_commodity, Toast.LENGTH_SHORT).show();
        }
        intent.putExtra("trolleys",selectedTrolleys);
        startActivity(intent);
    }

    private void clearCart() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.point_out)
                .setMessage(R.string.sure_clean_cart)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.setTrolleys(null);
                        adapter.notifyDataSetChanged();
                        rlBottom.setVisibility(View.GONE);
                        mLvCart.setVisibility(View.GONE);
                        mTvNoLogin.setVisibility(View.GONE);
                        mTvNoCart.setVisibility(View.VISIBLE);
                    }
                })
                .setNegativeButton(R.string.cancel,null)
                .show();
        CartService.clearTrolley();
    }

    private void deleteSelectedItems() {
        List<String> id=new ArrayList<>();
        List<Trolley> newTrolleys=new ArrayList<>();
        List<Trolley> trolleys=adapter.getTrolleys();
        for(Trolley t:trolleys){
            if(t.isSelected()){
                id.add(t.getId()+"");
            }else{
                newTrolleys.add(t);
            }
        }

        adapter.setTrolleys(newTrolleys);
        adapter.notifyDataSetChanged();

        if(newTrolleys==null||newTrolleys.size()==0){
            rlBottom.setVisibility(View.GONE);
            mLvCart.setVisibility(View.GONE);
            mTvNoLogin.setVisibility(View.GONE);
            mTvNoCart.setVisibility(View.VISIBLE);
        }

        for(String tid:id){
            CartService.deleteTrolley(tid);
        }

    }

    private class OnGoCommodityClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Trolley t= (Trolley) parent.getAdapter().getItem(position);
            Intent intent=new Intent(getActivity(),CommodityDetailActivity.class);
            intent.putExtra("commodityNo",t.getCommodityNo());
            intent.putExtra("storeNo",t.getStoreNo());
            Log.e(TAG,t.getCommodityNo());
            Log.e(TAG,t.getStoreNo());
            startActivity(intent);
        }
    }
}
