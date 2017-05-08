package com.ship.pojo.error;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 * 银行账户消息提示
 */
public enum BankMess {

    BANKNAMENULL("0001", "银行名称不能为空! "),

    BANKIDNULL("0002", "银行账户Id不能为空! "),

    ACCOUNTERROR("0004", "银行账户名称错误! "),

    USERBANKNULL("0005", "该用户未添加银行账户! "),

    BANKACCOUNTNULL("0003", "银行账户信息为空! ");


    private String code;
    private String message;
    private String ns = "COM.SHIP.BANK";

    BankMess(String code,String message){
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
