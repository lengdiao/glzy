package com.ecard.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Cache {
    private Long id;

    private String openid;

    private String code;

    private BigDecimal orderamount;

    private Integer disease;

    private Date ordertime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public BigDecimal getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(BigDecimal orderamount) {
        this.orderamount = orderamount;
    }

    public Integer getDisease() {
        return disease;
    }

    public void setDisease(Integer disease) {
        this.disease = disease;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }
}