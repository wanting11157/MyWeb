package com.wanting.me.entity;

import java.io.Serializable;
import java.sql.Date;

public class Base implements Serializable {
    private String updateTime;
    private String createTime;
    private String remark;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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
