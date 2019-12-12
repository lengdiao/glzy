package com.ecard.entity;

import java.util.Date;

public class DrOrDis {
    private Long id;

    private Long drno;

    private Long disno;

    private Integer vipcn;

    private String code;

    private Double codemoney;

    private Double dismoney;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDrno() {
        return drno;
    }

    public void setDrno(Long drno) {
        this.drno = drno;
    }

    public Long getDisno() {
        return disno;
    }

    public void setDisno(Long disno) {
        this.disno = disno;
    }

    public Integer getVipcn() {
        return vipcn;
    }

    public void setVipcn(Integer vipcn) {
        this.vipcn = vipcn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Double getCodemoney() {
        return codemoney;
    }

    public void setCodemoney(Double codemoney) {
        this.codemoney = codemoney;
    }

    public Double getDismoney() {
        return dismoney;
    }

    public void setDismoney(Double dismoney) {
        this.dismoney = dismoney;
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