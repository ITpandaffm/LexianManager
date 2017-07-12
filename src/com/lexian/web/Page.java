package com.lexian.web;

public class Page {
	
	
	private int pageNo=1;
	
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
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
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
