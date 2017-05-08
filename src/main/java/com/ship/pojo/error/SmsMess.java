package com.ship.pojo.error;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 */
public enum SmsMess {


    SMSTYPE("0001", "验证码类型不能为空! "),

    SMSCODENULL("0002", "验证码不能为空! "),

    SMSCODEERROR("0003", "验证码错误! "),

    SMSCODEEXPIRED("0004", "验证码已过期! "),

    SMSTYPERANGE("0005", "操作类型超出范围值! "),

    SMSCODEC("0006", "获取验证码频繁! ") ,

    CHECKERROR("0007", "验证失败! ") ,

    EMAILNULL("0009", "邮箱不能为空! ") ,

    CHECKSUCCESS("0008", "验证成功! ");

    private String code;
    private String message;
    private String ns = "COM.SHIP.SMSCODE";

    SmsMess(String code,String message){
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
