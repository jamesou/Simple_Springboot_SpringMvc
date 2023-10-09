package com.jamesou.springbootmvc.bean;

import java.util.Date;

/**
 * @author jamesou
 * @create 2020-05-10 16:11
 */
public class Information {
    private String cno;
    private String rno;
    private Date begintime;
    private double deposit;

    public Information() {
    }

    public Information(String cno, String rno, Date begintime, double deposit) {
        this.cno = cno;
        this.rno = rno;
        this.begintime = begintime;
        this.deposit = deposit;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }


    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
}
