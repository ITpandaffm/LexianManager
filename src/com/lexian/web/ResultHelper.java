/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.web;

import com.lexian.utils.Constant;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class ResultHelper {
	private int code;
	private Object data;
	
	public ResultHelper(){
		
	}
	
	public ResultHelper(int code){
		this.code = code;
		this.data = Constant.messages.get(code);
	}
	
	public ResultHelper(int code, Object data){
		this.code = code;
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
