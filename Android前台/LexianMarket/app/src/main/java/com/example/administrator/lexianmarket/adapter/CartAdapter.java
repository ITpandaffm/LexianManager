/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.bean.user.Trolley;
import com.example.administrator.lexianmarket.service.CartService;
import com.example.administrator.lexianmarket.utils.BitmapUtilsHelper;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class CartAdapter extends BaseAdapter {

    private Context context;
    private BitmapUtils bitmapUtils;
    private List<Trolley> trolleys;


    public CartAdapter(Context context) {
        this.context = context;
        bitmapUtils= BitmapUtilsHelper.getBitmapUtils(context);
    }

    public List<Trolley> getTrolleys() {
        return trolleys;
    }

    public void setTrolleys(List<Trolley> trolleys) {
        this.trolleys = trolleys;
    }

    public void addNewTrolleys(List<Trolley> trolleys){
        this.trolleys.addAll(trolleys);
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {

        if(trolleys==null){
            return 0;
        }

        return trolleys.size();
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

        if(trolleys==null){
            return null;
        }

        return trolleys.get(position);
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

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.00");
        CartViewHolder vw=null;

        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.item_cart,null,false);
            vw=new CartViewHolder();
            vw.ivCover = (ImageView) convertView.findViewById(R.id.iv_cover);
            vw.tvCommodityName = (TextView) convertView.findViewById(R.id.tv_commodity_name);
            vw.tvStoreName = (TextView) convertView.findViewById(R.id.tv_store_name);
            vw.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            vw.ivDelete = (ImageView) convertView.findViewById(R.id.iv_delete);

            vw.tvDown = (TextView) convertView.findViewById(R.id.tv_down);
            vw.tvPlus = (TextView) convertView.findViewById(R.id.tv_plus);
            vw.tvTotalAmount = (TextView) convertView.findViewById(R.id.tv_total_amount);
            vw.tvNum = (TextView) convertView.findViewById(R.id.tv_num);

            vw.cbItem = (CheckBox) convertView.findViewById(R.id.cb_item);

            convertView.setTag(vw);
        }else{
            vw= (CartViewHolder) convertView.getTag();
        }
        Trolley trolley=trolleys.get(position);
        bitmapUtils.display(vw.ivCover,trolley.getCommodity().getPictureUrl());
        vw.tvCommodityName.setText(trolley.getCommodity().getName());
        vw.tvStoreName.setText(trolley.getStore().getStoreName());
        vw.tvPrice.setText("单价：￥"+trolley.getListPrice());
        vw.tvNum.setText(trolley.getAmont()+"");
        vw.tvTotalAmount.setText("共计：￥"+myformat.format(trolley.getTotalPrice()));
        bitmapUtils.display(vw.ivCover,trolley.getCommodity().getPictureUrl());
        bindData(vw.ivDelete,position);
        bindData(vw.tvPlus,position);
        bindData(vw.tvDown,position);
        bindData(vw.cbItem,position);


        setListener(vw);

        return convertView;
    }
    private void bindData(View v,Object data){
        v.setTag(data);
    }


    private void setListener(CartViewHolder vw) {
        vw.ivDelete.setOnClickListener(new OnDeleteListener());

        View.OnClickListener onClickListener=new OnTrolleyClickListener();

        vw.tvDown.setOnClickListener(onClickListener);
        vw.tvPlus.setOnClickListener(onClickListener);
        vw.cbItem.setOnClickListener(onClickListener);
    }

    private class CartViewHolder {
        public CheckBox cbItem;
        public ImageView ivCover;
        public TextView tvCommodityName;
        public TextView tvStoreName;
        public TextView tvPrice;
        public ImageView ivDelete;

        public TextView tvPlus;
        public TextView tvDown;
        public TextView tvNum;
        public TextView tvTotalAmount;

    }
    private class OnDeleteListener implements View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(final View v) {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.dialog)
                    .setMessage(R.string.confirm_delete_this)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Integer position= (Integer) v.getTag();
                            Trolley trolley=trolleys.get(position);
                            trolleys.remove(trolley);
                            CartService.deleteTrolley(trolley.getId()+"");
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton(R.string.cancel,null)
                    .show();
        }
    }


    private class OnTrolleyClickListener implements View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            int position= (int) v.getTag();
            Trolley trolley=trolleys.get(position);

            switch (v.getId()){

                case R.id.tv_down:
                    if(trolley.getAmont()>1){
                        trolley.setAmont(trolley.getAmont()-1);
                        trolley.setTotalPrice(trolley.getAmont()*trolley.getListPrice());
                        CartService.updateTrolley(trolley);
                    }
                    break;
                case R.id.tv_plus:
                    trolley.setAmont(trolley.getAmont()+1);
                    trolley.setTotalPrice(trolley.getAmont()*trolley.getListPrice());
                    CartService.updateTrolley(trolley);
                    break;
                case R.id.cb_item:

                    if(trolley.isSelected()){
                        trolley.setSelected(false);
                    }else{
                        trolley.setSelected(true);
                    }
                    break;
            }
            notifyDataSetChanged();
        }
    }
}
