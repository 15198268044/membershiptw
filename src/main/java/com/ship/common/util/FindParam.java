package com.ship.common.util;

import java.util.Date;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/9.
 */
public class FindParam {
    /**
     *
     */
    private  String serialnumber;
    /**
     *
     */
    private  String username;
    /**
     * 联系电话
     */
    private  String telphone;
    /**
     * 银行账户
     */
    private  String bankAccount;
    /**
     *
     */
    private  Long bankId;
    /**
     *
     */
    private  Long userId;
    /**
     *
     */
    private  Integer state;
    /**
     *
     */
    private  Date startDateTime;
    /**
     *
     */
    private  Date endDateTime;

    public  FindParam(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }


    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }


    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
}
