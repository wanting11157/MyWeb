package com.wanting.me.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 返回单个结果对象
 */
public class ResponseResult implements Serializable {
    private Integer code;
    private String msg;
    private Object data;


    public Integer getCode() {

        if(this.code == null){
            this.code = WebResponse.SUCCESS;
        }
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        if(this.msg == null){
            this.msg = WebResponse.MSG_SUCCESS;
        }
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
