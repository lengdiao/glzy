package com.ecard.entity;

import java.util.Date;

public class DrugStoreCheckMallOrder {
    
	    private int id;
	    private long orderNo;
	    private long drugStoreNo;
	    private int isRemind;
	    private long createUser;
	    private Date createTime;
	    private long updateUser;
	    private Date updateTime;
	    private int version;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public long getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(long orderNo) {
			this.orderNo = orderNo;
		}
		public long getDrugStoreNo() {
			return drugStoreNo;
		}
		public void setDrugStoreNo(long drugStoreNo) {
			this.drugStoreNo = drugStoreNo;
		}
		public int getIsRemind() {
			return isRemind;
		}
		public void setIsRemind(int isRemind) {
			this.isRemind = isRemind;
		}
		public long getCreateUser() {
			return createUser;
		}
		public void setCreateUser(long createUser) {
			this.createUser = createUser;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public long getUpdateUser() {
			return updateUser;
		}
		public void setUpdateUser(long updateUser) {
			this.updateUser = updateUser;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		public int getVersion() {
			return version;
		}
		public void setVersion(int version) {
			this.version = version;
		} 
	    
	    
}
