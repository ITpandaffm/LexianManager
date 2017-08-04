/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.utils;

import java.util.HashMap;
import java.util.Map;

public class Constant {
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public static Map<Integer, String> messages;
	
	public static final int CODE_SUCCESS = 1;				// 操作成功
	public static final int CODE_ENTITY_NOT_FOUND = -1;		// 找不到实体
	public static final int CODE_INVALID_PARAMETER = -2;	// 参数无效
	public static final int CODE_ENTITY_DUPLICATED = -3;	// 该实体已经存在，因此不能再次创建
	public static final int CODE_ENTITY_IN_USE = -4;		// 该实体仍被使用，因此不能被删除
	public static final int CODE_PERMISSION_DENIED = -5;	// 没有执行该操作的权限
	public static final int CODE_LOGIN_REQUIRED = -6;		// 用户没有登录
	public static final int CODE_LOGIN_FAILED = -7;			// 登录失败
	public static final int CODE_EXECUTE_ERROR = -99;		// 服务器执行出错
	public static final int CODE_UNKNOWN_REASON = -100;		// 不明原因的错误
	
	public static final int CODE_NO_PRIVILEGE = -10;		//沒有权限
	public static final int CODE_NO_AMONT =-250;				//库存不足
	
	public static final int CODE_BALANCE_NO_ENOUGH =-500;    //余额不足
	public static final int CODE_PAY_PASSWORD_ERROR =-501;	//支付密码错误
	public static final int CODE_NO_LOGIN =-101;
	public static final int CODE_PASSWORD_ERROR =-123;
	public static final int CODE_PHONE_USED =-1234;
	public static final int CODE_STATE_FORBID=-200;
	static{
		messages = new HashMap<Integer, String>();
		messages.put(CODE_SUCCESS, "操作成功");
		messages.put(CODE_ENTITY_NOT_FOUND, "找不到实体");
		messages.put(CODE_INVALID_PARAMETER, "参数无效");
		messages.put(CODE_ENTITY_DUPLICATED, "该实体已经存在");
		messages.put(CODE_ENTITY_IN_USE, "该实体不能删除");
		messages.put(CODE_PERMISSION_DENIED, "没有执行该操作的权限");
		messages.put(CODE_LOGIN_REQUIRED, "用户没有登录");
		messages.put(CODE_LOGIN_FAILED, "登录失败");
		messages.put(CODE_EXECUTE_ERROR, "服务器执行出错");
		messages.put(CODE_UNKNOWN_REASON, "未知的原因");
		messages.put(CODE_NO_PRIVILEGE, "沒有权限");
		
		messages.put(CODE_NO_AMONT, "库存不足");
		messages.put(CODE_BALANCE_NO_ENOUGH, "余额不足");
		messages.put(CODE_PAY_PASSWORD_ERROR, "支付密码密码错误");
		messages.put(CODE_NO_LOGIN, "请先登录");
		messages.put(CODE_PASSWORD_ERROR, "密码错误");
		messages.put(CODE_PHONE_USED, "手机号已被占用");
		messages.put(CODE_STATE_FORBID, "该用户已被禁用，请联系管理员");
	}
}
