package com.ecard.pojo;

/**
 *分页参数bean
 */
public class PageParam {
    // 当前页
    private Integer page = 1;
    // 每页显示的总条数
    private Integer size = 10;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
    
    
    
}
