package com.ecard.entity;

import java.util.Date;
import java.util.Set;

public class CloudPassInfo {
    private Long cloudpassno;

    private String password;

    private String name;
    
    private String sex;

    private String cloudpasskind;

	private String phone;
    // 身份证号码
    private String idNo;

    private Integer disableflag;

    private String createuser;

    private Date createtime;
    
    private String introducer;

    private String updateuser;

    private Date updatetime;

    private Integer version;
    
    private Set<Role> roles;

    public Long getCloudpassno() {
        return cloudpassno;
    }

    public void setCloudpassno(Long cloudpassno) {
        this.cloudpassno = cloudpassno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCloudpasskind() {
        return cloudpasskind;
    }

    public void setCloudpasskind(String cloudpasskind) {
        this.cloudpasskind = cloudpasskind == null ? null : cloudpasskind.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	 public String getIdNo() {
			return idNo;
		}

	public void setIdNo(String idNo) {
			this.idNo = idNo;
		}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}
	
	
}