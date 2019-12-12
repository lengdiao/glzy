package com.ecard.pojo.queryResult;

public class DrugStorePersonQr {
	private Long id;

    private Long cloudPassNo;

    private String name;

    private String phone;

    private Long drugStoreNo;
    
    private String drugStoreName;
    
    private Integer disableFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getDrugStoreNo() {
		return drugStoreNo;
	}

	public void setDrugStoreNo(Long drugStoreNo) {
		this.drugStoreNo = drugStoreNo;
	}

	public String getDrugStoreName() {
		return drugStoreName;
	}

	public void setDrugStoreName(String drugStoreName) {
		this.drugStoreName = drugStoreName;
	}

	public Integer getDisableFlag() {
		return disableFlag;
	}

	public void setDisableFlag(Integer disableFlag) {
		this.disableFlag = disableFlag;
	}
    
    
}
