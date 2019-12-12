package com.ecard.entity;

import java.util.Date;

public class Message {
    private Long messageno;

    private Integer messagekind;

    private String messagebrief;

    private Long mallno;

    private Integer readstatus;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private Integer version;

    public Long getMessageno() {
        return messageno;
    }

    public void setMessageno(Long messageno) {
        this.messageno = messageno;
    }

    public Integer getMessagekind() {
        return messagekind;
    }

    public void setMessagekind(Integer messagekind) {
        this.messagekind = messagekind;
    }

    public String getMessagebrief() {
        return messagebrief;
    }

    public void setMessagebrief(String messagebrief) {
        this.messagebrief = messagebrief == null ? null : messagebrief.trim();
    }

    public Long getMallno() {
        return mallno;
    }

    public void setMallno(Long mallno) {
        this.mallno = mallno;
    }

    public Integer getReadstatus() {
        return readstatus;
    }

    public void setReadstatus(Integer readstatus) {
        this.readstatus = readstatus;
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