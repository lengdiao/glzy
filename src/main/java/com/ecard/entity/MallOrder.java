package com.ecard.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MallOrder {
    private Long mallNo;

    private Long cloudPassNo;

    private Long drugId;

    private Integer number;

    private Long drugStoreNo;

    private String drugStorePerson;

    private String idCardImg;

    private String agreeImg;

    private String recordImg;

    private String recipeImg;

    private String diagnosisImg;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Date orderTime;

    private Integer checkStatus;

    private String cause;

    private Integer payStatus;

    private BigDecimal payAmount;

    private Date payTime;

    private Integer shippingStatus;

    private String shippingCompany;

    private String shippingContext;

    private String shippingNo;

    private Date shippingTime;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer version;

    public Long getMallNo() {
        return mallNo;
    }

    public void setMallNo(Long mallNo) {
        this.mallNo = mallNo;
    }

    public Long getCloudPassNo() {
        return cloudPassNo;
    }

    public void setCloudPassNo(Long cloudPassNo) {
        this.cloudPassNo = cloudPassNo;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getDrugStoreNo() {
        return drugStoreNo;
    }

    public void setDrugStoreNo(Long drugStoreNo) {
        this.drugStoreNo = drugStoreNo;
    }

    public String getDrugStorePerson() {
        return drugStorePerson;
    }

    public void setDrugStorePerson(String drugStorePerson) {
        this.drugStorePerson = drugStorePerson == null ? null : drugStorePerson.trim();
    }

    public String getIdCardImg() {
        return idCardImg;
    }

    public void setIdCardImg(String idCardImg) {
        this.idCardImg = idCardImg == null ? null : idCardImg.trim();
    }

    public String getAgreeImg() {
        return agreeImg;
    }

    public void setAgreeImg(String agreeImg) {
        this.agreeImg = agreeImg == null ? null : agreeImg.trim();
    }

    public String getRecordImg() {
        return recordImg;
    }

    public void setRecordImg(String recordImg) {
        this.recordImg = recordImg == null ? null : recordImg.trim();
    }

    public String getRecipeImg() {
        return recipeImg;
    }

    public void setRecipeImg(String recipeImg) {
        this.recipeImg = recipeImg == null ? null : recipeImg.trim();
    }

    public String getDiagnosisImg() {
        return diagnosisImg;
    }

    public void setDiagnosisImg(String diagnosisImg) {
        this.diagnosisImg = diagnosisImg == null ? null : diagnosisImg.trim();
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany == null ? null : shippingCompany.trim();
    }

    public String getShippingContext() {
        return shippingContext;
    }

    public void setShippingContext(String shippingContext) {
        this.shippingContext = shippingContext == null ? null : shippingContext.trim();
    }

    public String getShippingNo() {
        return shippingNo;
    }

    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo == null ? null : shippingNo.trim();
    }

    public Date getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Date shippingTime) {
        this.shippingTime = shippingTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}