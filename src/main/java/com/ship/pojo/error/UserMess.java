package com.ship.pojo.error;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 * 用户消息提示
 */
public enum  UserMess {


    PHONENULL("0001","手机号不能为空! "),

    VIPNAMENULL("0002","会员昵称不能为空! "),

    IDCARDNULL("0003","身份证号不能为空! "),

    PASSWORDNULL("0004","密码不能为空! "),

    REALNAMENULL("0005","真实姓名不能为空! "),

    REFERRERCODEID("0006","推荐人不能为空! "),

    USERIDNULL("0007","用户ID不能为空! "),

    USERNULL("0008","用户不存在! "),

    ACCOUNTNULL("0009","账户不能为空! "),

    ACCOUNTORPASSERROR("0010","用户名或密码错误! "),

    PHONENUAALL("0011","没有该账户信息，请注册! "),

    ISLOCK("0012","账户锁定中，无法操作! "),

    OLDPASSNULL("0013","旧密码不能为空! "),

    NEWPASSNULL("0014","新密码不能为空! "),

    OLDPASSERROR("0015","旧密码错误! "),

    OLDTELNULL("0016","原手机号不能为空! "),

    NEWTELNULL("0017","新手机号不能为空! "),

    ACCOUNTTOREGI("0018","该手机号码已被注册! "),

    OLDPHONERROR("0019","操作状态不能为空! "),

    ACCOUNTNOTNULL("0021","该账户已存在! "),

    VIPNAMENOTNULL("0022","用户名已存在! "),

    REFERCODENOTNULL("0023","推荐人不存在! "),

    USERDATANULL("0020","用户数据为空! ");



    private String code;
    private String message;
    private String ns = "COM.SHIP.USER";

    UserMess(String code,String message){
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
