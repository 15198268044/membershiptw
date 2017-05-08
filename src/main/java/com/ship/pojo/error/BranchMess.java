package com.ship.pojo.error;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/9.
 */
public enum BranchMess {


    TATALMONEYNULL("0001", "分润金额不能为空! "),

    ERRORACCOUNT("0003", "没有该账号推荐人信息! "),

    ACCOUNTNULL("0002", "账户不能为空! ");

    private String code;
    private String message;
    private String ns = "COM.SHIP.BRANCH";

    BranchMess(String code,String message){
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
