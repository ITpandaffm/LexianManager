/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.mall.commodity.bean.CommodityStore;
import com.lexian.mall.user.bean.OrderItem;
import com.lexian.mall.user.bean.Orders;
import com.lexian.mall.user.bean.User;
import com.lexian.mall.user.bean.Wallet;
import com.lexian.mall.user.bean.WalletRecord;
import com.lexian.mall.user.dao.CommodityStoreDao;
import com.lexian.mall.user.dao.OrderItemDao;
import com.lexian.mall.user.dao.OrdersDao;
import com.lexian.mall.user.dao.UserDao;
import com.lexian.mall.user.dao.WalletDao;
import com.lexian.mall.user.dao.WalletRecordDao;
import com.lexian.mall.user.helper.CashItem;
import com.lexian.mall.user.helper.OrderInfo;
import com.lexian.mall.user.service.UserService;
import com.lexian.utils.Constant;
import com.lexian.utils.UrlContstant;
import com.lexian.web.ResultHelper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WalletDao walletDao;
	
	@Autowired
	private CommodityStoreDao commodityStoreDao;
	
	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private WalletRecordDao walletRecordDao;
	
	@Override
	public ResultHelper signIn(String phone, String password) {
		
		
		User user=userDao.getUserByPhoneAndPassword(phone, password);
		
		ResultHelper result;
		
		if(user!=null){
			if(user.getStatus()==1){
				result=new ResultHelper(Constant.CODE_SUCCESS, user);
			}else{
				result=new ResultHelper(Constant.CODE_STATE_FORBID, user);
			}
			
		}else{
			result=new ResultHelper(Constant.CODE_LOGIN_FAILED);
		}
		return result;
	}


	@Override
	public ResultHelper cash(OrderInfo orderInfo, String password, List<CashItem> cashItems) {
		
		Wallet wallet=walletDao.getWalletByUserIdAndPayPassword(orderInfo.getUserId(), password);
		
		//1.验证支付密码
		if(wallet==null){
			return new ResultHelper(Constant.CODE_PAYPASSWORD_ERROR);
		}
		double sum=0;
		//1.验证库存,捎带计算总金额和记录每一样店铺商品的价格
		List<CommodityStore> commodityStores=new ArrayList<>();
		
		for(CashItem cashItem:cashItems){
			CommodityStore commodityStore=commodityStoreDao.getCommodityStoreByCommodityNoAndStoreNo(cashItem.getCommodityNo(), cashItem.getStoreNo());
			cashItem.setRealPrice(commodityStore.getRealPrice());
			sum+=commodityStore.getRealPrice()*cashItem.getNumber();
			if(commodityStore.getCommodityAmont()-commodityStore.getCommodityLockAmont()<cashItem.getNumber()){
				return new ResultHelper(Constant.CODE_NO_AMONT);
			}
			commodityStore.setCommodityAmont(commodityStore.getCommodityAmont()-cashItem.getNumber());
			commodityStores.add(commodityStore);
		}
		//2.判断余额是否充足
		if(sum>wallet.getBalance()){
			return new ResultHelper(Constant.CODE_BALANCE_NO_ENOUGH);
		}
		//3.完成真正的支付
		
		//3.1 将List<CashItem>按照 store 进行分类，使用Map的形式存放
		Map<String, List<CashItem>> storeCashItems = groupByStoreNo(cashItems);
		
		//3.2 生成一个订单编号，规则暂时定为当前的时间戳,然后初始化orders
		Orders orders = prepareOrders(orderInfo);
		
		//3.3.1为每一个 storeNo插入一条记录,并插入相应的orderItem
		
		Map<CommodityStore,Integer> csNumMap=new HashMap<>();
		for(Map.Entry<String, List<CashItem>> entry:storeCashItems.entrySet()){
			//计算总金额
			List<CashItem> items=entry.getValue();
			double total=0;
			for(CashItem item:items){
				total+=item.getNumber()*item.getRealPrice();
			}
			orders.setStoreNo(entry.getKey());
			orders.setTotalAmount(total);
			//向orders中插入记录
			ordersDao.insertOrders(orders);
			Integer ordersId=orders.getId();
			//向orderItem中批量插入数据
			List<OrderItem> orderItems=new ArrayList<>();
			for(CashItem item:items){
				OrderItem oi=new OrderItem();
				oi.setOrderId(ordersId);
				oi.setAmount(item.getNumber());
				oi.setListPrice(item.getRealPrice());
				oi.setTotalPrice(item.getNumber()*item.getRealPrice());
				oi.setCommodityNo(item.getCommodityNo());
				orderItems.add(oi);
				CommodityStore cs=new CommodityStore();
				cs.setStoreNo(orders.getStoreNo());
				cs.setCommodityNo(oi.getCommodityNo());
				csNumMap.put(cs, oi.getAmount());
			}
			orderItemDao.insertOrderItem(orderItems);
			
		}
		
		//4.向walletRecord中插入一条记录
		WalletRecord record = prepareWalletRecord(wallet, sum, orders);
		walletRecordDao.insertWalletRecord(record);
		
		//5.更改余额
		wallet.setBalance(wallet.getBalance()-sum);
		walletDao.updateBalance(wallet);
		
		//6.更新库存
		commodityStoreDao.updateCommodityStoreAmontByBatch(commodityStores);
		return new ResultHelper(Constant.CODE_SUCCESS);
	}


	private WalletRecord prepareWalletRecord(Wallet wallet, double sum, Orders orders) {
		WalletRecord record=new WalletRecord();
		record.setAmount(sum);
		record.setCreateTime(new Date());
		record.setDescription("使用钱包余额购物");
		record.setType(1);
		record.setResultType(1);
		record.setWalletId(wallet.getId());
		record.setOrderNo(orders.getOrderNo());
		return record;
	}


	private Orders prepareOrders(OrderInfo orderInfo) {
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddhhmmssSSS");
		Date time=new Date();
		Orders orders=new Orders();
		orders.setOrderNo(format.format(time));
		orders.setCreateTime(time);
		orders.setDeliveryType(orderInfo.getDeliveryType());
		orders.setPaymentType(orderInfo.getPaymentType());
		orders.setPaymentSubtype(orderInfo.getPaymentSubtype());
		orders.setUserId(orderInfo.getUserId());
		orders.setStates(2);
		return orders;
	}


	private Map<String, List<CashItem>> groupByStoreNo(List<CashItem> cashItems) {
		Map<String,List<CashItem>> storeCashItems=new HashMap<>();
		for(CashItem cashItem:cashItems){
			String storeNo=cashItem.getStoreNo();
			List<CashItem> items=storeCashItems.get(storeNo);
			if(items==null){
				items=new ArrayList<>();
				storeCashItems.put(storeNo, items);
			}
			items.add(cashItem);
		}
		return storeCashItems;
	}


	@Override
	public ResultHelper getUserWithWallet(String userId) {
		User user=userDao.getUserWithWalletById(userId);
		if(user==null){
			user=userDao.getUserById(userId);
		}else{
			if(user.getPortrait()!=null){
				user.setPortrait(UrlContstant.QINIU_ADDRESS+"/"+user.getPortrait());
			}
		}
		
		
		return new ResultHelper(Constant.CODE_SUCCESS,user );
	}


	@Override
	public ResultHelper getUser(String userId) {
		User user=userDao.getUserById(userId);
		if(user.getPortrait()!=null){
			user.setPortrait(UrlContstant.QINIU_ADDRESS+"/"+user.getPortrait());
		}
		return new ResultHelper(Constant.CODE_SUCCESS,user );
	}


	@Override
	public ResultHelper updatePassword(User user, String newPassword) {
		
		Integer num=userDao.checkPassword(user);
		ResultHelper result=null;
		if(num==0){
			result=new ResultHelper(Constant.CODE_PASSWORD_ERROR);
		}else{
			
			userDao.updatePassword(user.getId(),newPassword);
			result=new ResultHelper(Constant.CODE_SUCCESS);
		}
		
		return result;
	}


	@Override
	public ResultHelper registerUser(User user) {
		
		Integer num=userDao.checkIsPhoneUsed(user.getPhone());
		ResultHelper result=null;
		if(num>0){
			result=new ResultHelper(Constant.CODE_PHONE_USED);
		}else{
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
			user.setId(user.getUserName()+format.format(new Date()));
			userDao.addUser(user);
			result=new ResultHelper(Constant.CODE_SUCCESS,user.getId());
		}
		return result;
	}

	
}
