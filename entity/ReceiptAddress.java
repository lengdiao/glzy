package com.ecard.entity;

import java.util.Date;

public class ReceiptAddress {
    private Long addid;

    private Long ptno;

    private String address;

    private String addressdetail;

    private Integer mark;

    private Integer disableflag;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private Integer version;

    public Long getAddid() {
        return addid;
    }

    public void setAddid(Long addid) {
        this.addid = addid;
    }

    public Long getPtno() {
        return ptno;
    }

    public void setPtno(Long ptno) {
        this.ptno = ptno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAddressdetail() {
        return addressdetail;
    }

    public void setAddressdetail(String addressdetail) {
        this.addressdetail = addressdetail == null ? null : addressdetail.trim();
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
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