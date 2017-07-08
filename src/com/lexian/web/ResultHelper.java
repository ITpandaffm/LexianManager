package com.lexian.web;

import com.lexian.utils.Constant;

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
