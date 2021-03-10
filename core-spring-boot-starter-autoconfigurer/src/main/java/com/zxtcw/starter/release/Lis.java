package com.zxtcw.starter.release;

import java.util.Date;

/**
 * @comment
 * @author Walter(翟笑天)
 * @date 2021/3/5
 */
public class Lis {

    private String model;
    private Date begin;
    private Date end;
    private Integer concurrency;

    public Lis() {
        super();
    }

    public Lis(String model, Date begin, Date end, Integer concurrency) {
        this.model = model;
        this.begin = begin;
        this.end = end;
        this.concurrency = concurrency;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(Integer concurrency) {
        this.concurrency = concurrency;
    }
}
