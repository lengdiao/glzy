package com.ecard.entity;

import java.util.Date;

public class Sednsms {
 
	private Long sedneSmsNo ;
	private String phone ;
	private String code ;
	private Date createTime;
	private Date updateTime;
	private Date expiryDate;
	
	public Long getSedneSmsNo() {
		return sedneSmsNo;
	}
	public void setSedneSmsNo(Long sedneSmsNo) {
		this.sedneSmsNo = sedneSmsNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
    
}
