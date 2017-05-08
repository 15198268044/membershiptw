package com.ship.pojo.error;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 管理员信息
 */
public enum AdminMess {


    ACCOUNTNULL("0001", "账户不能为空! "),
    PASSWRODNULL("0002", "密码不能为空！"),
    ACCPASSERROR("0003", "账户或密码错误！");

    private String code;
    private String message;
    private String ns = "COM.SHIP.ADMIN";
///
    AdminMess(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.ns+"."+this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getNs() {
        return ns;
    }


}
