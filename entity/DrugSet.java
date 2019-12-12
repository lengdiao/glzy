package com.ecard.entity;

import java.util.Date;

import com.ecard.Constants;

public class DrugSet {
    private Long drugno;

    private String commonname;

    private String adultdosing;

    private String drugformat;

    private String drugunit;

    private Double drugprice;

    private Integer vipcn;
    
    private Integer disableflag;
       
    private String createuser = Constants.SYSTEM_USER;

    private Date createtime = new Date();

    private String updateuser;

    private Date updatetime;

    private Integer version;

    public Long getDrugno() {
        return drugno;
    }

    public void setDrugno(Long drugno) {
        this.drugno = drugno;
    }

    public String getCommonname() {
        return commonname;
    }

    public void setCommonname(String commonname) {
        this.commonname = commonname == null ? null : commonname.trim();
    }

    public String getAdultdosing() {
        return adultdosing;
    }

    public void setAdultdosing(String adultdosing) {
        this.adultdosing = adultdosing == null ? null : adultdosing.trim();
    }

    public String getDrugformat() {
        return drugformat;
    }

    public void setDrugformat(String drugformat) {
        this.drugformat = drugformat == null ? null : drugformat.trim();
    }

    public String getDrugunit() {
        return drugunit;
    }

    public void setDrugunit(String drugunit) {
        this.drugunit = drugunit == null ? null : drugunit.trim();
    }

    public Double getDrugprice() {
        return drugprice;
    }

    public void setDrugprice(Double drugprice) {
        this.drugprice = drugprice;
    }

    public Integer getVipcn() {
        return vipcn;
    }

    public void setVipcn(Integer vipcn) {
        this.vipcn = vipcn;
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
}