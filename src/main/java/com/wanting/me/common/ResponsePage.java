package com.wanting.me.common;

import java.util.List;

public class ResponsePage extends ResponseResult{
    private List<Object> listData;
    //现在第几页
    private Integer page;
    //每页有多少行
    private Integer rows;
    //一共有多少行
    private Integer total;

    public List<Object> getListData() {
        return listData;
    }

    public void setListData(List<Object> listData) {
        this.listData = listData;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ResponsePage{" +
                "listData=" + listData +
                ", page=" + page +
                ", rows=" + rows +
                ", total=" + total +
                '}';
    }
}
