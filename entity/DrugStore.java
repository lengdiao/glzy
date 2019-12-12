package com.ecard.entity;

import java.util.Date;

public class DrugStore {
    private Long drugstoreno;

	private Long cloudpassno;

	private String contact;
	
	private String name;

	private String telephone;
	
	private String principal;
	
	private String principalPhone;

	private String address;

	private Integer disableflag;

	private String createuser;

	private Date createtime;

	private String updateuser;

	private Date updatetime;

	private Integer version;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDrugstoreno() {
		return drugstoreno;
	}

	public void setDrugstoreno(Long drugstoreno) {
		this.drugstoreno = drugstoreno;
	}

	public Long getCloudpassno() {
		return cloudpassno;
	}

	public void setCloudpassno(Long cloudpassno) {
		this.cloudpassno = cloudpassno;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact == null ? null : contact.trim();
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Integer getDisableflag() {
		return disableflag;
	}

	public void setDisableflag(Integer disableflag) {
		this.disableflag = disableflag;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser == null ? null : createuser.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser == null ? null : updateuser.trim();
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPrincipalPhone() {
		return principalPhone;
	}

	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}
		
}