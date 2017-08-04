/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.utils;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王晨昕
 * @version 1.0
 */
public class AppConfig {
    // 网络调用超时
    public static final int TIME_OUT = 1000 * 20;

    // 网络调用结果码
    public static final int RESULT_SUCCESS_POSITIVE = 1;     // 服务器执行成功，并且返回了正面结果
    public static final int RESULT_FAIL_NEGTIVE = -1;       // 服务器执行成功，并且返回了负面结果
    public static final int RESULT_ERROR = -2;      // 服务器执行错误并返回了错误
    public static final int RESULT_TIMEOUT = 0;     // 超时，服务器没有返回

}
