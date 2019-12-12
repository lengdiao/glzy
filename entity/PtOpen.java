package com.ecard.entity;

public class PtOpen {
    private Long id;

    private Long ptno;

    private String openid;
    
    private Long disno;

    private Long drno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPtno() {
        return ptno;
    }

    public void setPtno(Long ptno) {
        this.ptno = ptno;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
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
    
    
}