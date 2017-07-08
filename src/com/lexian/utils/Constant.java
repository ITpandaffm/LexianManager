package com.lexian.utils;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	
public static Map<Integer, String> messages;
	
	public static final int code_success = 1;				// 操作成功
	public static final int code_entity_not_found = -1;		// 找不到实体
	public static final int code_invalid_parameter = -2;	// 参数无效
	public static final int code_entity_duplicated = -3;	// 该实体已经存在，因此不能再次创建
	public static final int code_entity_in_use = -4;		// 该实体仍被使用，因此不能被删除
	public static final int code_permission_denied = -5;	// 没有执行该操作的权限
	public static final int code_login_required = -6;		// 用户没有登录
	public static final int code_login_failed = -7;			// 登录失败
	public static final int code_execute_error = -99;		// 服务器执行出错
	public static final int code_unknown_reason = -100;		// 不明原因的错误
	
	public static final int code_no_privilege = -10;		//沒有权限
	
	static{
		messages = new HashMap<Integer, String>();
		messages.put(code_success, "操作成功");
		messages.put(code_entity_not_found, "找不到实体");
		messages.put(code_invalid_parameter, "参数无效");
		messages.put(code_entity_duplicated, "该实体已经存在");
		messages.put(code_entity_in_use, "该实体不能删除");
		messages.put(code_permission_denied, "没有执行该操作的权限");
		messages.put(code_login_required, "用户没有登录");
		messages.put(code_login_failed, "登录失败");
		messages.put(code_execute_error, "服务器执行出错");
		messages.put(code_unknown_reason, "未知的原因");
		messages.put(code_no_privilege, "沒有权限");
	}
}
