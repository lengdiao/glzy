package com.ecard.pojo;

import java.util.List;

public class PageInfo<T>{
	private List<T> rows;		//查询结果
	private long total;	//总数
	
	public PageInfo() {
		super();
	}
	
	public PageInfo(List<T> rows, long total) {
		super();
		this.rows = rows;
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setResult(List<T> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotalCount(long total) {
		this.total = total;
	}
	
	
	
	
}
