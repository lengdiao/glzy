package com.ecard.entity;

import java.util.Date;
import java.util.Set;

public class Role {
    private Long roleno;

    private String rolename;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private Integer version;
    
    private Set<Authority> Authoritys;
    
    public Long getRoleno() {
        return roleno;
    }

    public void setRoleno(Long roleno) {
        this.roleno = roleno;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
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
    
    public Set<Authority> getAuthoritys() {
		return Authoritys;
	}
    
    public void setAuthoritys(Set<Authority> authoritys) {
		Authoritys = authoritys;
	}
}