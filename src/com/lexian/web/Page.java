/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.web;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class Page {
	
	
	private Integer pageNo=1;
	
	private int pageSize=30;
	
	private long totalSize;
	
	private int pageNums=1;
	
	private Object data;
	
	public int getPageNums() {
		return pageNums;
	}
	public void setPageNums(int pageNums) {
		this.pageNums = pageNums;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		
		if(pageNo!=null&&pageNo>0){
			this.pageNo = pageNo;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalSize() {
		
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		

		if(totalSize>pageSize){
			if(totalSize%pageSize!=0){
				pageNums=(int) (totalSize/pageSize)+1;
			}else{
				pageNums=(int) (totalSize/pageSize);
			}
		}
		
		this.totalSize = totalSize;
	}
	
	
}
