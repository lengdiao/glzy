package com.ecard.entity;

import java.util.Date;

public class MedRecord {
    private Long medrecordno;

    private Long ptno;

    private Long drno;

    private String diagcontent;

    private String subjective;

    private String objective;

    private String plan;

    private Date visitdate;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private Integer version;

    public Long getMedrecordno() {
        return medrecordno;
    }

    public void setMedrecordno(Long medrecordno) {
        this.medrecordno = medrecordno;
    }

    public Long getPtno() {
        return ptno;
    }

    public void setPtno(Long ptno) {
        this.ptno = ptno;
    }

    public Long getDrno() {
        return drno;
    }

    public void setDrno(Long drno) {
        this.drno = drno;
    }

    public String getDiagcontent() {
        return diagcontent;
    }

    public void setDiagcontent(String diagcontent) {
        this.diagcontent = diagcontent == null ? null : diagcontent.trim();
    }

    public String getSubjective() {
        return subjective;
    }

    public void setSubjective(String subjective) {
        this.subjective = subjective == null ? null : subjective.trim();
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective == null ? null : objective.trim();
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan == null ? null : plan.trim();
    }

    public Date getVisitdate() {
        return visitdate;
    }

    public void setVisitdate(Date visitdate) {
        this.visitdate = visitdate;
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