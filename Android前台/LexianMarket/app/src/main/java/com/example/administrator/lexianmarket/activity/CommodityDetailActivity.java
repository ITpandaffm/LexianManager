/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.bean.commodity.Commodity;
import com.example.administrator.lexianmarket.bean.commodity.CommodityStore;
import com.example.administrator.lexianmarket.bean.commodity.Store;
import com.example.administrator.lexianmarket.bean.user.CommodityCollection;
import com.example.administrator.lexianmarket.bean.user.Trolley;
import com.example.administrator.lexianmarket.helper.LoginHelper;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.CartService;
import com.example.administrator.lexianmarket.service.CollectionService;
import com.example.administrator.lexianmarket.service.CommodityService;
import com.example.administrator.lexianmarket.utils.BitmapUtilsHelper;
import com.example.administrator.lexianmarket.utils.Constant;

import java.util.ArrayList;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class CommodityDetailActivity extends BaseActivity {
    private ImageView ivCommodityDetailPicture;//商品图片
    private TextView tvCommodityDetailName;//商品名字
    private TextView tvCommodityDetailRealPrice;//现价
    private TextView tvCommodityStoreName;//门店名
    private TextView tvCommodityDetailIntroduce;
    private Button bnCommodityBuy;//购买按钮
    private Button bnCommodityCart;//放入购物车按钮
    private LinearLayout llCommodityCollection;//收藏按钮
    private LinearLayout llStore;//店铺按钮
    private RelativeLayout llStoreIntroduce;//店铺介绍
    private EditText etDialogCartCount;
    private RelativeLayout rlCommodityIntroduce;
    private ImageView ivStarCollection;//
    private TextView tvStarCollection;//
    private TextView tvDialogStoreName;
    private TextView tvDialogStoreNo;
    private TextView tvDialogStoreAddress;
    private TextView tvDialogStoreIntroduce;
    private  int count=1;
    private  boolean goCart=false;
    private  boolean buyCart=false;
    private Dialog dialog;
    private View inflate;
    private CommodityStore commodityStore;
    private CommodityCollection cc;
    private boolean isCollection =false;
    private static final int COMMODITY_STORE =1;
    private static final int CART=2;
    private static final int HASING_COMMODITY_COLLECTION =3;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case COMMODITY_STORE:
                    ResultHelper<CommodityStore> result= ( ResultHelper<CommodityStore>) msg.obj;
                    if (result.getCode()== Constant.CODE_SUCCESS){
                        getData(result);
                    }
                    break;
                case HASING_COMMODITY_COLLECTION:
                    ResultHelper<Boolean> result3= (ResultHelper<Boolean>) msg.obj;
                    if (result3.getCode()== Constant.CODE_SUCCESS){
                        getCommodittyCollectionData(result3);
                        removeMessages(HASING_COMMODITY_COLLECTION);
                    }
                    break;
            }
            super.handleMessage(msg);
        }

    };

    private void getCommodittyCollectionData(ResultHelper<Boolean> result) {
        isCollection = result.getData();

        if (isCollection ==true){
            int color = Color.RED;
            ivStarCollection.setImageResource(R.mipmap.att_on);
            tvStarCollection.setTextColor(color);
            isCollection =true;
        }else{
            int color = Color.BLACK;
            ivStarCollection.setImageResource(R.mipmap.button_star);
            tvStarCollection.setTextColor(color);
            isCollection =false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_detail);
            initViews();
            initData();
            setListener();
    }

    @Override
    protected void onResume(){

        if (LoginHelper.isLogin){
            CollectionService.hasCommodityInCommodityCollection(cc,handler);
        }
        initData();
        super.onResume();
    }

    private void initData() {
        cc = new CommodityCollection();
        ((TextView)findViewById(R.id.common_title_content)).setText(R.string.commodity_detail);
        ((TextView)findViewById(R.id.common_title_right_text)).setText(R.string.first_page);
        cc.setCommodityNo(this.getIntent().getStringExtra("commodityNo"));
        cc.setStoreNo(this.getIntent().getStringExtra("storeNo"));
        CommodityService.getCommodity(cc.getCommodityNo(),cc.getStoreNo(),handler);
    }

    private void setListener() {
        View.OnClickListener listener=new CommodityDetailActivity
                .OnCommodityIntroduceClickListener();
        llStore.setOnClickListener(listener);
        llStoreIntroduce.setOnClickListener(listener);
        rlCommodityIntroduce.setOnClickListener(listener);
        ((TextView)findViewById(R.id.common_title_right_text)).setOnClickListener(listener);
        View.OnClickListener isLoginListener=new
                CommodityDetailActivity.OnIsLoginListenerClickListener();
        bnCommodityBuy.setOnClickListener(isLoginListener);
        bnCommodityCart.setOnClickListener(isLoginListener);
        llCommodityCollection.setOnClickListener(isLoginListener);
    }

    private class OnCommodityIntroduceClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.ll_store:
                    intent = new Intent(CommodityDetailActivity.this,GoodsListActivity.class);
                    intent.putExtra("storeNo",commodityStore.getStoreNo());
                    break;
                case R.id.ll_store_introduce:
                    initDialogStoreView();
                    break;
                case R.id.rl_commodity_introduce:
                    break;
                case R.id.common_title_right_text:
                    intent = new Intent(CommodityDetailActivity.this,MainActivity.class);
                    break;
            }
            if (intent !=null){
                startActivity(intent);
            }
        }
    }


    private class OnIsLoginListenerClickListener  implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (LoginHelper.isLogin){
                isLoginListenerClickListener(v.getId());
            }else{
                Toast.makeText(CommodityDetailActivity.this, R.string.login_first,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CommodityDetailActivity.this,LoginActivity.class);
                startActivity(intent);
            }

        }
    }
    public void isLoginListenerClickListener(int id){
        switch (id){
            case R.id.bn_commodity_buy:
                buyCart=true;
                initDialogCartView();
                break;
            case R.id.bn_commodity_cart:
                goCart=true;
                initDialogCartView();
                break;
            case R.id.ll_commodity_collection:
                if (isCollection ==false) {
                    int color = Color.RED;
                    ivStarCollection.setImageResource(R.mipmap.att_on);
                    tvStarCollection.setTextColor(color);
                    CollectionService.addCommodityCollection(cc);
                    Toast.makeText(CommodityDetailActivity.this, R.string.had_collection,
                            Toast.LENGTH_SHORT).show();
                    isCollection =true;
                }else{
                    int color = Color.BLACK;
                    ivStarCollection.setImageResource(R.mipmap.button_star);
                    tvStarCollection.setTextColor(color);
                    CollectionService.deleteCommodityCollection(cc);
                    Toast.makeText(CommodityDetailActivity.this, R.string.remove_collection,
                            Toast.LENGTH_SHORT).show();
                    isCollection =false;
                }
                break;
        }

    }

    private void initViews() {
        ivCommodityDetailPicture = (ImageView)
                findViewById(R.id.iv_commodity_detail_picture);
        tvCommodityDetailName = (TextView) findViewById(R.id.tv_commodity_detail_name);
        tvCommodityDetailRealPrice = (TextView)
                findViewById(R.id.tv_commodity_detail_realPrice);
        tvCommodityStoreName = (TextView) findViewById(R.id.tv_commodity_storeName);
        bnCommodityBuy = (Button) findViewById(R.id.bn_commodity_buy);
        bnCommodityCart = (Button) findViewById(R.id.bn_commodity_cart);
        llCommodityCollection = (LinearLayout) findViewById(R.id.ll_commodity_collection);
        llStore =(LinearLayout) findViewById(R.id.ll_store);
        llStoreIntroduce = (RelativeLayout) findViewById(R.id.ll_store_introduce);
        rlCommodityIntroduce = (RelativeLayout) findViewById(R.id.rl_commodity_introduce);
        ivStarCollection = (ImageView) findViewById(R.id.iv_star_collection);
        tvStarCollection =(TextView) findViewById(R.id.tv_star_collection);
        tvCommodityDetailIntroduce = (TextView)
                findViewById(R.id.tv_commodity_detail_introduce);

    }

    public void getData( ResultHelper<CommodityStore> result){
        commodityStore = result.getData();
        BitmapUtilsHelper.getBitmapUtils(this)
                .display(ivCommodityDetailPicture,commodityStore.getCommodity()
                        .getPictureUrl());
        tvCommodityDetailName.setText(commodityStore.getCommodity().getName());
        tvCommodityStoreName.setText(commodityStore.getStore().getStoreName());
        tvCommodityDetailRealPrice.setText("￥"+(float) commodityStore.getRealPrice());
        tvCommodityDetailIntroduce.setText(commodityStore.getCommodity().getIntroduce());


        cc.setCommodityNo(commodityStore.getCommodityNo());
        cc.setStoreNo(commodityStore.getStoreNo());

    }

    private void initDialogStoreView() {
        dialog = new Dialog(CommodityDetailActivity.this,R.style.ActionSheetDialogStyle);
        inflate=LayoutInflater.from(CommodityDetailActivity.this)
                .inflate(R.layout.dialog_store_introduce,null,false);
        dialog.setContentView(inflate);
        Window dialogWindow=dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        lp.y=80;
        lp.width=d.getWidth();
        lp.height=(int)(d.getHeight()*0.6);
        dialogWindow.setAttributes(lp);
        dialog.show();

        tvDialogStoreAddress = (TextView) inflate.findViewById(R.id.tv_dialog_store_address);
        tvDialogStoreIntroduce = (TextView)inflate.findViewById(R.id.tv_dialog_store_introduce);
        tvDialogStoreName = (TextView)inflate.findViewById(R.id.tv_dialog_store_name);
        tvDialogStoreNo = (TextView) inflate.findViewById(R.id.tv_dialog_store_no);
        tvDialogStoreAddress = (TextView) inflate.findViewById(R.id.tv_dialog_store_address);
        Button bn_dialog_store_button= (Button) inflate.findViewById(R.id.bn_dialog_store_button);
        //
        tvDialogStoreNo.setText(commodityStore.getStore().getStoreNo());
        tvDialogStoreName.setText(commodityStore.getStore().getStoreName());
        tvDialogStoreIntroduce.setText(commodityStore.getStore().getIntroduce());
        tvDialogStoreAddress.setText(commodityStore.getStore().getStoreAddress());
        bn_dialog_store_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    private void initDialogCartView(){
        dialog = new Dialog(CommodityDetailActivity.this,R.style.ActionSheetDialogStyle);
        inflate=LayoutInflater.from(CommodityDetailActivity.this)
                .inflate(R.layout.dialog_cart,null,false);
        dialog.setContentView(inflate);
        Window dialogWindow=dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        lp.y=80;
        lp.width=d.getWidth();
        lp.height=(int)(d.getHeight()*0.6);
        dialogWindow.setAttributes(lp);
        dialog.show();
        //initView
        ImageView iv_dialog_cart_picture= (ImageView) inflate
                .findViewById(R.id.iv_dialog_cart_picture);
        ImageView iv_dialog_cart_back= (ImageView) inflate
                .findViewById(R.id.iv_dialog_cart_back);
        Button btn_go_Cart =(Button) inflate.findViewById(R.id.btn_go_Cart);
        TextView tv_dialog_cart_name= (TextView) inflate.findViewById(R.id.tv_dialog_cart_name);
        TextView tv_dialog_cart_amount= (TextView) inflate
                .findViewById(R.id.tv_dialog_cart_amount);
        TextView tv_dialog_cart_price= (TextView) inflate
                .findViewById(R.id.tv_dialog_cart_price);
        ImageButton iv_dialog_cart_reduce= (ImageButton) inflate
                .findViewById(R.id.iv_dialog_cart_reduce);
        ImageButton iv_dialog_cart_add= (ImageButton) inflate
                .findViewById(R.id.iv_dialog_cart_add);
        etDialogCartCount = (EditText) inflate.findViewById(R.id.et_dialog_cart_count);
        //initData
        BitmapUtilsHelper.getBitmapUtils(this).display(iv_dialog_cart_picture,
                commodityStore.getCommodity().getPictureUrl());
        tv_dialog_cart_name.setText(commodityStore.getCommodity().getName());
        tv_dialog_cart_price.setText("￥"+(float) commodityStore.getRealPrice());
        tv_dialog_cart_amount.setText(getString(R.string.amount)+commodityStore.getCommodityAmont()+getString(R.string.commodity_jian));
        etDialogCartCount.setText(count+"");
        etDialogCartCount.setSelectAllOnFocus(true);
        //setOnClickListener
        iv_dialog_cart_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                buyCart=false;
                goCart=false;
                count=1;
                dialog.dismiss();
            }
        });
        iv_dialog_cart_add.setOnClickListener(new OnaddClickListener());
        iv_dialog_cart_reduce.setOnClickListener(new OnreduceClickListener());
        etDialogCartCount.setOnClickListener(new OnCountClickListener());
        btn_go_Cart.setOnClickListener(new OnGoCartClickListener());
    }

    private class OnaddClickListener  implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (count <= commodityStore.getCommodityAmont()){
            count++;
            etDialogCartCount.setText(count+"");
            }else {
                count=commodityStore.getCommodityAmont();
                etDialogCartCount.setText(count+"");
            }
        }

    }
    private class OnreduceClickListener  implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (count >1){
                count--;
                etDialogCartCount.setText(count+"");
            }else {
                count=1;
                etDialogCartCount.setText(count+"");
            }
        }

    }
    private class OnCountClickListener  implements OnClickListener {
        @Override
        public void onClick(View v) {
            count=Integer.parseInt(etDialogCartCount.getText().toString());
            if (count<1){
                count=1;
                etDialogCartCount.setText(count+"");
            }else if(count>commodityStore.getCommodityAmont()){
                count=commodityStore.getCommodityAmont();
                etDialogCartCount.setText(count+"");
            }else{
                etDialogCartCount.setText(count+"");
            }
        }

    }
    private class OnGoCartClickListener  implements OnClickListener {
        @Override
        public void onClick(View v) {
            if(goCart == true) {
                Trolley trolley = new Trolley();
                trolley.setAmont(Integer.parseInt(etDialogCartCount.getText().toString()));
                trolley.setCommodityNo(commodityStore.getCommodityNo());
                trolley.setStoreNo(commodityStore.getStoreNo());
                CartService.addCommodityToTrolley(trolley);
                Toast.makeText(CommodityDetailActivity.this, R.string.had_go_cart,
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                buyCart=false;
                goCart=false;
                count=1;
            }else if (buyCart == true){
                Intent intent = new Intent(CommodityDetailActivity.this,
                        ConfirmOrdersActitvity.class);

                ArrayList<Trolley> trolleys = new ArrayList<Trolley>();
                Trolley trolley = new Trolley();
                trolley.setStoreNo(commodityStore.getStoreNo());
                trolley.setCommodityNo(commodityStore.getCommodityNo());
                trolley.setAmont(Integer.parseInt(etDialogCartCount.getText().toString()));
                trolley.setListPrice(commodityStore.getRealPrice());
                Commodity commodity = new Commodity();
                commodity.setPictureUrl(commodityStore.getCommodity().getPictureUrl());
                commodity.setName(commodityStore.getCommodity().getName());
                Store store = new Store();
                store.setStoreName(commodityStore.getStore().getStoreName());
                trolley.setStore(store);
                double totalPrice = Integer.parseInt(etDialogCartCount.getText()
                        .toString())*commodityStore.getRealPrice();
                trolley.setTotalPrice(totalPrice);
                trolley.setCommodity(commodity);
                trolleys.add(trolley);
               intent.putExtra("trolleys",trolleys);

               startActivity(intent);
                Toast.makeText(CommodityDetailActivity.this, R.string.confirm_orders,
                        Toast.LENGTH_SHORT).show();
                buyCart=false;
                goCart=false;
                count=1;
                dialog.dismiss();
            }
        }
    }
}

