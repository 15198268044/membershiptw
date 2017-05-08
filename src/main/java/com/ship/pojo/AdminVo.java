package com.ship.pojo;

import java.util.Date;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 */
public class AdminVo {

    /**
     *账户
     */
    private String account;
    /**
     *登录时间
     */
    private String loginTime;
    /**
     *登录次数
     */
    private Integer loginnum;
    /**
     *登录Ip
     */
    private String loginIp;


    public AdminVo(){

    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String  loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getLoginnum() {
        return loginnum;
    }

    public void setLoginnum(Integer loginnum) {
        this.loginnum = loginnum;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

}
