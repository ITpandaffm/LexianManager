/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.adapter.CategoryGoodsAdapter;
import com.example.administrator.lexianmarket.adapter.CategoryListAdapter;
import com.example.administrator.lexianmarket.bean.commodity.Category;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.CategoryService;
import com.example.administrator.lexianmarket.utils.Constant;

import java.util.List;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王晨昕
 * @version 1.0
 */
public class CategoryFragment extends Fragment {

    private static final int FIRST_CATEGORY=1;
    private static final int SECOND_CATEGORY=2;

    List<Category> categoryList;
    List<Category> categoryGoodsList;
    int currentPosition = 0;
    CategoryListAdapter categoryListAdapter;
    CategoryGoodsAdapter categoryGoodsAdapter;
    ListView categoryListView;
    ListView categoryGoodsListView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case FIRST_CATEGORY:

                    ResultHelper<List<Category>> result = (ResultHelper<List<Category>>) msg.obj;
                    if(result.getCode() == Constant.CODE_SUCCESS){
                        initFirstCategory(result);
                    }
                    break;
                case SECOND_CATEGORY:

                    ResultHelper<List<Category>> result2 = (ResultHelper<List<Category>>) msg.obj;
                    if(result2.getCode() == Constant.CODE_SUCCESS){
                        initSecondCategory(result2);
                    }
                    break;

            }
            super.handleMessage(msg);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);


        initView(rootView);

        initFirstData();

        initCategoryListAdapter();

        initCategoryGoodsAdapter();

        setCategoryListListener();

        return rootView;
    }

    public void initView(View rootView) {
        categoryListView = (ListView) rootView.findViewById(R.id.category_list_listview);
        categoryGoodsListView = (ListView) rootView.findViewById(R.id.category_goods_listview);
    }


    public void initCategoryListAdapter() {

        categoryListAdapter = new CategoryListAdapter(getActivity(), currentPosition);

        categoryListView.setAdapter(categoryListAdapter);

    }

    public void initCategoryGoodsAdapter() {

        categoryGoodsAdapter = new CategoryGoodsAdapter(getActivity());

        categoryGoodsListView.setAdapter(categoryGoodsAdapter);


    }

    public void setCategoryListListener() {
        categoryListView.setOnItemClickListener(new categoryListItemClickListener());

    }

    private class categoryListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            currentPosition = position;

            categoryListAdapter.setCurrentPosition(currentPosition);
            categoryListAdapter.notifyDataSetChanged();

            initSecondData(categoryList.get(currentPosition).getId());
        }
    }


    public void initFirstData() {
        CategoryService.getFirstCategory(handler);
    }

    public void initFirstCategory(ResultHelper<List<Category>> result) {
        categoryList = result.getData();
        categoryListAdapter.setCategoryList(categoryList);
        categoryListAdapter.notifyDataSetChanged();
        initSecondData(categoryList.get(currentPosition).getId());
    }

    public void initSecondData(int id){
        CategoryService.getSecondCategory(id, handler);
    }

    public void initSecondCategory(ResultHelper<List<Category>> result) {
        categoryGoodsList = result.getData();
        categoryGoodsAdapter.setCategoryList(categoryGoodsList);
        categoryGoodsAdapter.notifyDataSetChanged();
    }

}
