package com.ecard.entity;

import java.util.Date;

public class DrInfo {
    private Long drno;

    private Long cloudpassno;
    
    private String cloudPassKind;
    private String name;
    private String telephone;
    
    private String phone;
    private String idNo;
    private Integer isDoctor;

    private String hospital;

    private String province;

    private String address;

    private Integer disableflag;
    
    private int age ;
    private String chiefNo;   // 医生职位
	private String practiceProfile;  // 医生执业情况简介
	private String introducer;   // 推荐人
	private String company;     //  推荐人公司
	private String title;       //  职称	
	private String drTitleCert;  // 医师资格证号
 
    private String createuser;

    private Date createTime;

    private String updateuser;

    private Date updatetime;

    private Integer version;

    private byte[] signature;

    public Long getDrno() {
        return drno;
    }

    public void setDrno(Long drno) {
        this.drno = drno;
    }

    public Long getCloudpassno() {
        return cloudpassno;
    }

    public void setCloudpassno(Long cloudpassno) {
        this.cloudpassno = cloudpassno;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital == null ? null : hospital.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getChiefNo() {
		return chiefNo;
	}

	public void setChiefNo(String chiefNo) {
		this.chiefNo = chiefNo;
	}

	public String getPracticeProfile() {
		return practiceProfile;
	}

	public void setPracticeProfile(String practiceProfile) {
		this.practiceProfile = practiceProfile;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDrTitleCert() {
		return drTitleCert;
	}

	public void setDrTitleCert(String drTitleCert) {
		this.drTitleCert = drTitleCert;
	}

	public String getCloudPassKind() {
		return cloudPassKind;
	}

	public void setCloudPassKind(String cloudPassKind) {
		this.cloudPassKind = cloudPassKind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Integer getIsDoctor() {
		return isDoctor;
	}

	public void setIsDoctor(Integer isDoctor) {
		this.isDoctor = isDoctor;
	}
        
}