package com.ecard.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Description:
 * Created by 少坤 on 2016/3/9.
 */
@Entity
@Table(name = "Remind")
public class Remind {
    private int id;
    private long roleNo;
    private int role;
    private String kind;
    private String content;
    private int isRead;
    private int disableFlag;
    private long createUser;
    private Date createTime;
    private long updateUser;
    private Date updateTime;
    private int version;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RoleNo")
    public long getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(long roleNo) {
        this.roleNo = roleNo;
    }

    @Basic
    @Column(name = "Role")
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Basic
    @Column(name = "Kind")
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Basic
    @Column(name = "Content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "isRead")
    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    @Basic
    @Column(name = "DisableFlag")
    public int getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(int disableFlag) {
        this.disableFlag = disableFlag;
    }

    @Basic
    @Column(name = "CreateUser")
    public long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(long createUser) {
        this.createUser = createUser;
    }

    @Basic
    @Column(name = "CreateTime")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "UpdateUser")
    public long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(long updateUser) {
        this.updateUser = updateUser;
    }

    @Basic
    @Column(name = "UpdateTime")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Version
    @Column(name = "Version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Remind remind = (Remind) o;

        if (id != remind.id) return false;
        if (roleNo != remind.roleNo) return false;
        if (role != remind.role) return false;
        if (isRead != remind.isRead) return false;
        if (disableFlag != remind.disableFlag) return false;
        if (createUser != remind.createUser) return false;
        if (updateUser != remind.updateUser) return false;
        if (version != remind.version) return false;
        if (kind != null ? !kind.equals(remind.kind) : remind.kind != null) return false;
        if (content != null ? !content.equals(remind.content) : remind.content != null) return false;
        if (createTime != null ? !createTime.equals(remind.createTime) : remind.createTime != null) return false;
        return updateTime != null ? updateTime.equals(remind.updateTime) : remind.updateTime == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (roleNo ^ (roleNo >>> 32));
        result = 31 * result + role;
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + isRead;
        result = 31 * result + disableFlag;
        result = 31 * result + (int) (createUser ^ (createUser >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (int) (updateUser ^ (updateUser >>> 32));
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + version;
        return result;
    }
}
