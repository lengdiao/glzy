package com.ecard.entity;

public class FactoryPerson {
    private Long id;

    private Long cloudPassNo;

    private String name;

    private String phone;

    private String sex;

    private Long mechanismNo;

    private Long departmentNo;

    private Integer disableFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCloudPassNo() {
        return cloudPassNo;
    }

    public void setCloudPassNo(Long cloudPassNo) {
        this.cloudPassNo = cloudPassNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Long getMechanismNo() {
        return mechanismNo;
    }

    public void setMechanismNo(Long mechanismNo) {
        this.mechanismNo = mechanismNo;
    }

    public Long getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(Long departmentNo) {
        this.departmentNo = departmentNo;
    }

    public Integer getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(Integer disableFlag) {
        this.disableFlag = disableFlag;
    }
}