package com.ecard.entity;

import java.util.Date;

public class PtInfo {
    private Long ptno;

    private Long cloudpassno;

    private Integer disease;

    private String sex;

    private Date birthday;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private Integer version;

    public Long getPtno() {
        return ptno;
    }

    public void setPtno(Long ptno) {
        this.ptno = ptno;
    }

    public Long getCloudpassno() {
        return cloudpassno;
    }

    public void setCloudpassno(Long cloudpassno) {
        this.cloudpassno = cloudpassno;
    }

    public Integer getDisease() {
        return disease;
    }

    public void setDisease(Integer disease) {
        this.disease = disease;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
         this.sex = (sex == null ? null : sex.trim());
    	
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
}