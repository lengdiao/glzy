package com.ecard.entity;

import java.util.Date;

public class Authority {
    private Long authorityno;

    private String authorityname;

    private String evntname;

    private String createuser;

    private Date creattime;

    private String updateuser;

    private Date updatetime;

    private Integer version;

    public Long getAuthorityno() {
        return authorityno;
    }

    public void setAuthorityno(Long authorityno) {
        this.authorityno = authorityno;
    }

    public String getAuthorityname() {
		return authorityname;
	}

	public void setAuthorityname(String authorityname) {
		this.authorityname = authorityname;
	}

	public String getEvntname() {
        return evntname;
    }

    public void setEvntname(String evntname) {
        this.evntname = evntname == null ? null : evntname.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
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