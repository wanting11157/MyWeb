package com.wanting.me.entity;

import java.sql.Date;

public class Base {
    private Date updateTime;
    private Date createTime;
    private String remark;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Base{" +
                "updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
