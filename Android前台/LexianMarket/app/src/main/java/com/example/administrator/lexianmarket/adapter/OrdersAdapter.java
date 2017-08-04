/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.activity.GoodsListActivity;
import com.example.administrator.lexianmarket.bean.user.Orders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class OrdersAdapter extends BaseAdapter {

    private Context context;
    private List<Orders> ordersList;

    public OrdersAdapter(Context context) {
        this.context = context;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }
    public void addNewOrders(List<Orders> ordersList){
        this.ordersList.addAll(ordersList);
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {

        if(ordersList==null){
            return 0;
        }
        return ordersList.size();
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
        if(ordersList==null){
            return null;
        }

        return ordersList.get(position);
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

        OrdersViewHolder vw=null;

        if(convertView==null){
            convertView=LayoutInflater.from(context)
                    .inflate(R.layout.item_order_list,null,false);

            vw=new OrdersViewHolder();
            vw.tvOrderNo = (TextView) convertView.findViewById(R.id.tv_order_no);
            vw.tvOrderCreateTime =(TextView) convertView.findViewById(R.id.tv_order_create_time);
            vw.tvTotalAmount = (TextView) convertView.findViewById(R.id.tv_total_amount);
            vw.tvOrderState = (TextView) convertView.findViewById(R.id.tv_order_state);
            vw.tvStoreName = (TextView) convertView.findViewById(R.id.tv_store_name);
            vw.llGoStore = (LinearLayout) convertView.findViewById(R.id.ll_go_store);
            convertView.setTag(vw);

        }else{
            vw= (OrdersViewHolder) convertView.getTag();
        }
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Orders orders=ordersList.get(position);
        vw.tvOrderNo.setText(orders.getOrderNo());
        vw.tvOrderCreateTime.setText(format.format(new Date(orders.getCreateTime())));
        vw.tvTotalAmount.setText(orders.getTotalAmount()+"");
        vw.tvOrderState.setText(getStateBystateNo(orders.getStates()));
        vw.tvStoreName.setText(orders.getStore().getStoreName());

        bindData(vw.llGoStore,position);
        setListener(vw);

        return convertView;
    }

    private void setListener(OrdersViewHolder vw) {
        vw.llGoStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= (int) v.getTag();
                Orders orders=ordersList.get(position);

                Intent intent=new Intent(context, GoodsListActivity.class);
                intent.putExtra("storeNo",orders.getStoreNo());
                context.startActivity(intent);
            }
        });
    }

    private void bindData(View view, int position) {
        view.setTag(position);
    }

    private String getStateBystateNo(Integer state){

        String result="";

        switch (state){

            case 1:
                result=context.getString(R.string.state_paymente);
                break;
            case 2:
                result=context.getString(R.string.state_deliver_goods);
                break;
            case 3:
                result=context.getString(R.string.state_deliver_goods_had);
                break;
            case 4:
                result=context.getString(R.string.statement_sheet);
                break;
        }

        return result;
    }
    private static class OrdersViewHolder{

        public TextView tvOrderNo;
        public TextView tvOrderCreateTime;
        public TextView tvTotalAmount;
        public TextView tvOrderState;
        public TextView tvStoreName;
        private LinearLayout llGoStore;
    }

}
