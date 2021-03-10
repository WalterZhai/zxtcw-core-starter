package com.zxtcw.starter.entity;

import java.math.BigInteger;

/**
 * @comment
 * @author Walter(翟笑天)
 * @date 2021/3/5
 */
public class TableEntity {
    private String code;
    private String msg;
    private BigInteger count;
    private Object data;

    public TableEntity() {
    }

    public TableEntity(Object datas,BigInteger count) {
        this();
        this.code = "0";//0代表OK
        this.msg = "";
        this.count = count;
        this.data = datas;
    }

    public TableEntity(Throwable t) {
        this();
        this.code = "1";//代表异常
        this.msg = t.getMessage();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
