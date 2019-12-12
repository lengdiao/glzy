package com.ecard.pojo.queryResult;

import java.util.Date;

public class PtInfoQrr {
	private Long ptNo;

    private Long cloudPassNo;

    private String name;

    private String address;

    private String idCard;

    private String phone;

    private String volunteerName;

    private String volPhone;

    private Long drugStorePersonNo;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer version;
    
    private Integer disableFlag;

	public Long getPtNo() {
		return ptNo;
	}

	public void setPtNo(Long ptNo) {
		this.ptNo = ptNo;
	}

	public Long getCloudPassNo() {
		return cloudPassNo;
	}

	public void setCloudPassNo(Long cloudPassNo) {
		this.cloudPassNo = cloudPassNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVolunteerName() {
		return volunteerName;
	}

	public void setVolunteerName(String volunteerName) {
		this.volunteerName = volunteerName;
	}

	public String getVolPhone() {
		return volPhone;
	}

	public void setVolPhone(String volPhone) {
		this.volPhone = volPhone;
	}

	public Long getDrugStorePersonNo() {
		return drugStorePersonNo;
	}

	public void setDrugStorePersonNo(Long drugStorePersonNo) {
		this.drugStorePersonNo = drugStorePersonNo;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getDisableFlag() {
		return disableFlag;
	}

	public void setDisableFlag(Integer disableFlag) {
		this.disableFlag = disableFlag;
	}
    
    
}
