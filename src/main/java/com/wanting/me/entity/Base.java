package com.wanting.me.entity;

import java.sql.Date;

public class Base {
    private Date update_time;
    private Date create_time;
    private String remark;

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
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
                "update_time=" + update_time +
                ", create_time=" + create_time +
                ", remark='" + remark + '\'' +
                '}';
    }
}
