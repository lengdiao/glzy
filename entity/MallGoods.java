package com.ecard.entity;

public class MallGoods {
    private Long id;

    private String openid;

    private Long drugno;

    private Integer number;

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

    public Long getDrugno() {
        return drugno;
    }

    public void setDrugno(Long drugno) {
        this.drugno = drugno;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}