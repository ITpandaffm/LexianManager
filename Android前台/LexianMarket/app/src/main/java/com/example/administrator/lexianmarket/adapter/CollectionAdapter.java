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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.bean.user.CommodityCollection;
import com.example.administrator.lexianmarket.service.CollectionService;
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
public class CollectionAdapter extends BaseAdapter {


    private Context context;
    private List<CommodityCollection> collectionList;
    private BitmapUtils bitmapUtils;
    public CollectionAdapter(Context context) {
        this.context = context;
        bitmapUtils= BitmapUtilsHelper.getBitmapUtils(context);
    }

    public void setCollectionList(List<CommodityCollection> collectionList) {
        this.collectionList = collectionList;
    }
    public void addNewCollections(List<CommodityCollection> collectionList){
        this.collectionList.addAll(collectionList);
    }
    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {

        if(collectionList==null){
            return 0;
        }

        return collectionList.size();
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


        if(collectionList==null){
            return null;
        }

        return collectionList.get(position);
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

        CollectionViewHolder vw=null;

        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.item_collection,null,false);
            vw=new CollectionViewHolder();
            vw.ivCover = (ImageView) convertView.findViewById(R.id.iv_cover);
            vw.tvCommodityName = (TextView) convertView.findViewById(R.id.tv_commodity_name);
            vw.tvCcStoreName = (TextView) convertView.findViewById(R.id.tv_cc_store_name);
            vw.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            vw.ivDelete = (ImageView) convertView.findViewById(R.id.iv_delete);
            convertView.setTag(vw);
        }else{
            vw= (CollectionViewHolder) convertView.getTag();
        }
        CommodityCollection cc=collectionList.get(position);
        bitmapUtils.display(vw.ivCover,cc.getCommodity().getPictureUrl());
        vw.tvCommodityName.setText(cc.getCommodity().getName());
        vw.tvCcStoreName.setText(cc.getStore().getStoreName());
        vw.tvPrice.setText("￥"+cc.getCommodityStore().getRealPrice());

        vw.ivDelete.setTag(position);
        vw.ivDelete.setOnClickListener(new OnDeleteListener());

        return convertView;
    }

    private class CollectionViewHolder{
        public ImageView ivCover;
        public TextView tvCommodityName;
        public TextView tvCcStoreName;
        public TextView tvPrice;
        public ImageView ivDelete;
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
                    .setTitle(R.string.point_out)
                    .setMessage(R.string.confirm_delete_if)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Integer position= (Integer) v.getTag();
                            CommodityCollection cc= collectionList.get(position);
                            collectionList.remove(cc);
                            notifyDataSetChanged();
                            CollectionService.deleteCommodityCollection(cc);
                        }
                    })
                    .setNegativeButton(R.string.cancel,null)
                    .show();
        }
    }
}
