package com.ecard.entity;

import java.util.Date;

public class DisInfo {
    private Long disno;
    
    private String name;

    private Long cloudpassno;

    private String telephone;

    private String contact;

    private String address;
   
	private String email;
    
    private String disLevel;
   
    private Long disLeader;
    
    private String disLeaderPhone;

    private Integer disableflag;
    private String cloudPassKind;
    
    private Integer isDoctor;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private Integer version;

    public Long getDisno() {
        return disno;
    }

    public void setDisno(Long disno) {
        this.disno = disno;
    }

    public Long getCloudpassno() {
        return cloudpassno;
    }

    public void setCloudpassno(Long cloudpassno) {
        this.cloudpassno = cloudpassno;
    }

    public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
     
    public String getDisLevel() {
		return disLevel;
	}

	public void setDisLevel(String disLevel) {
		this.disLevel = disLevel;
	}

	public Long getDisLeader() {
		return disLeader;
	}

	public void setDisLeader(Long disLeader) {
		this.disLeader = disLeader;
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

	public Integer getIsDoctor() {
		return isDoctor;
	}

	public void setIsDoctor(Integer isDoctor) {
		this.isDoctor = isDoctor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisLeaderPhone() {
		return disLeaderPhone;
	}

	public void setDisLeaderPhone(String disLeaderPhone) {
		this.disLeaderPhone = disLeaderPhone;
	}

	public String getCloudPassKind() {
		return cloudPassKind;
	}

	public void setCloudPassKind(String cloudPassKind) {
		this.cloudPassKind = cloudPassKind;
	}
       
}