/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.lexianmarket.R;
import com.example.administrator.lexianmarket.activity.GoodsListActivity;
import com.example.administrator.lexianmarket.adapter.CitysAdapter;
import com.example.administrator.lexianmarket.adapter.StoreAdapter;
import com.example.administrator.lexianmarket.bean.city.Citys;
import com.example.administrator.lexianmarket.bean.commodity.Store;
import com.example.administrator.lexianmarket.helper.ResultHelper;
import com.example.administrator.lexianmarket.service.CitysService;
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
public class StoreFragment extends Fragment {

    private Spinner spinnerProvince;
    private Spinner spinnerCity;
    private Spinner spinnerDistrict;
    private Spinner spinnerLocation;

    private CitysAdapter provinceAdapter;
    private CitysAdapter cityAdapter;
    private CitysAdapter districtAdapter;
    private StoreAdapter storeAdapter;

    private ImageView ivLocation;

    private static final int OBTAINED_PROVINCE=1;
    private static final int OBTAINED_CITY=2;
    private static final int OBTAINED_DISTRICT =3;
    private static final int OBTAINED_STORE =4;
    private static final int CHANGE_PROVINCE=5;
    private static final int UPDATE_LOCATION=6;

    private String provinceId;
    private String cityId;
    private String countryId;
    private String storeNo;

    private Button btnGoStore;

    private String province;
    private String city;
    private String district;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    public boolean isLocation=false;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ResultHelper<List<Citys>> result=null;
            switch (msg.what){

                case OBTAINED_PROVINCE:
                    result= (ResultHelper<List<Citys>>) msg.obj;
                    if(result.getCode()== Constant.CODE_SUCCESS){
                        bindCityAdapterData(result,provinceAdapter,spinnerProvince);
                    }
                    break;
                case OBTAINED_CITY:
                    result= (ResultHelper<List<Citys>>) msg.obj;
                    if(result.getCode()== Constant.CODE_SUCCESS){

                        bindCityAdapterData(result,cityAdapter,spinnerCity);
                        if(isLocation){
                            setLocation(cityAdapter,city,spinnerCity);
                        }

                    }
                    break;
                case OBTAINED_DISTRICT:
                    result= (ResultHelper<List<Citys>>) msg.obj;
                    if(result.getCode()== Constant.CODE_SUCCESS){

                        bindCityAdapterData(result,districtAdapter,spinnerDistrict);
                        if(isLocation){
                            setLocation(districtAdapter,district,spinnerDistrict);
                            isLocation=false;
                        }

                    }
                    break;
                case OBTAINED_STORE:
                    ResultHelper<List<Store>> sresult= (ResultHelper<List<Store>>) msg.obj;
                    if(sresult.getCode()== Constant.CODE_SUCCESS){
                        bindStoreAdapterData(sresult);
                    }

                    break;
                case CHANGE_PROVINCE:
                    isLocation=true;
                    setLocation(provinceAdapter,province,spinnerProvince);
                    break;
                case UPDATE_LOCATION:
                    isLocation=true;
                    setLocation(provinceAdapter,province,spinnerProvince);
                    setLocation(cityAdapter,city,spinnerCity);
                    setLocation(districtAdapter,district,spinnerDistrict);
                    break;
            }

            super.handleMessage(msg);
        }
    };

    private void bindStoreAdapterData(ResultHelper<List<Store>> sresult) {

        List<Store> stores=sresult.getData();
        if(stores!=null&&stores.size()>0){
            storeAdapter.setStores(stores);
            storeAdapter.notifyDataSetChanged();
        }

    }



    private void clearData(int type) {

        switch (type){
            case OBTAINED_PROVINCE:
                cityAdapter.setCities(null);
                cityAdapter.notifyDataSetChanged();

                districtAdapter.setCities(null);
                districtAdapter.notifyDataSetChanged();

                storeAdapter.setStores(null);
                storeAdapter.notifyDataSetChanged();

                cityId=null;
                countryId=null;
                storeNo=null;
                break;
            case OBTAINED_CITY:
                districtAdapter.setCities(null);
                districtAdapter.notifyDataSetChanged();

                storeAdapter.setStores(null);
                storeAdapter.notifyDataSetChanged();

                countryId=null;
                storeNo=null;
                break;
            case OBTAINED_DISTRICT:
                storeAdapter.setStores(null);
                storeAdapter.notifyDataSetChanged();

                storeNo=null;
                break;
        }


    }




    private void bindCityAdapterData(ResultHelper<List<Citys>> result,
                                     CitysAdapter adapter, Spinner spinner) {
        List<Citys> cityses=result.getData();
        adapter.setCities(cityses);
        adapter.notifyDataSetChanged();
        //spinner.setSelection(0);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store, container, false);
        initViews(rootView);
        setListener();
        initAdapters();
        setAdapter();
        initData();
        return rootView;
    }


    private void setAdapter() {
        spinnerProvince.setAdapter(provinceAdapter);
        spinnerDistrict.setAdapter(districtAdapter);
        spinnerCity.setAdapter(cityAdapter);
        spinnerLocation.setAdapter(storeAdapter);
    }

    private void initAdapters() {
        provinceAdapter=new CitysAdapter(getActivity());
        cityAdapter=new CitysAdapter(getActivity());
        districtAdapter=new CitysAdapter(getActivity());
        storeAdapter=new StoreAdapter(getActivity());
    }

    private void initData() {
        CitysService.getCities(null,OBTAINED_PROVINCE,handler);
        initLocation();
    }

    private void initLocation() {

        mLocationClient = new LocationClient(getActivity());
        mLocationClient.registerLocationListener(myListener);
        setLocationOption();
    }

    @Override
    public void onResume() {
        if(!mLocationClient.isStarted()){
            mLocationClient.start();
        }

        super.onResume();
    }

    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        //就是这个方法设置为true，才能获取当前的位置信息
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        mLocationClient.setLocOption(option);
    }

    private void setListener() {
        spinnerCity.setOnItemSelectedListener(new OnCitySelectListener());
        spinnerProvince.setOnItemSelectedListener(new OnProvinceSelectListener());
        spinnerDistrict.setOnItemSelectedListener(new OnDistrictSelectListener());
        spinnerLocation.setOnItemSelectedListener(new OnLocationSelectListener());
        btnGoStore.setOnClickListener(new OnGoStoreClickListener());
        ivLocation.setOnClickListener(new OnLocationClickListener());
    }

    private void initViews(View rootView) {
        spinnerProvince = (Spinner) rootView.findViewById(R.id.store_spinner_province);
        spinnerCity = (Spinner) rootView.findViewById(R.id.store_spinner_city);
        spinnerDistrict = (Spinner) rootView.findViewById(R.id.store_spinner_district);
        spinnerLocation = (Spinner) rootView.findViewById(R.id.store_spinner_location);
        btnGoStore = (Button) rootView.findViewById(R.id.btn_go_store);

        ivLocation= (ImageView) rootView.findViewById(R.id.iv_location);
    }


    private class OnCitySelectListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Citys citys= (Citys) parent.getAdapter().getItem(position);
            cityId=citys.getId()+"";
            clearData(OBTAINED_CITY);
            CitysService.getCities(citys.getId()+"", OBTAINED_DISTRICT,handler);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnDistrictSelectListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Citys citys= (Citys) parent.getAdapter().getItem(position);
            countryId=citys.getId()+"";
            clearData(OBTAINED_DISTRICT);
            CitysService.getStoresByCitysId(provinceId,cityId,countryId,handler);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnProvinceSelectListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Citys citys= (Citys) parent.getAdapter().getItem(position);
            provinceId=citys.getId()+"";
            clearData(OBTAINED_PROVINCE);
            CitysService.getCities(citys.getId()+"",OBTAINED_CITY,handler);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnLocationSelectListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Store store= (Store) parent.getAdapter().getItem(position);
            storeNo=store.getStoreNo();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnGoStoreClickListener implements View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

            if(storeNo==null){
                Toast.makeText(getActivity(), R.string.select_one_store, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent=new Intent(getActivity(), GoodsListActivity.class);
            intent.putExtra("storeNo",storeNo);
            startActivity(intent);
        }
    }
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            //经纬度
            double lati = location.getLatitude();
            double longa = location.getLongitude();

            if(location.getProvince()!=null&&!location.getProvince().equals(province)){
                province=location.getProvince();
                handler.sendEmptyMessage(CHANGE_PROVINCE);
            }
            if(location.getCity()!=null){
                city=location.getCity();
            }
            if(location.getDistrict()!=null){
                district =location.getDistrict();
            }


            //打印出当前位置
            Log.i("TAG", "location.getAddrStr()=" + location.getAddrStr());
            //打印出当前城市
            Log.i("TAG", "location.getCity()=" + location.getCity());
            //返回码
            int i = location.getLocType();
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {}

    }

    private void setLocation(CitysAdapter adapter, String name, Spinner spinner) {
        for(int i=0;i<adapter.getCount();i++){
            Citys citys= (Citys) adapter.getItem(i);
            if(citys.getCity().equals(name)){
                adapter.notifyDataSetChanged();
                spinner.setSelection(i);
                break;
            }
        }
    }

    private class OnLocationClickListener implements View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            handler.sendEmptyMessage(UPDATE_LOCATION);
        }
    }

    @Override
    public void onDestroy() {
        mLocationClient.stop();
        super.onDestroy();
    }
}
