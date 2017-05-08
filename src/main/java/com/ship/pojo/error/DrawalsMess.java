package com.ship.pojo.error;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/7.
 * 提现消息提示
 */
public enum DrawalsMess {


    TATALMONEYNULL("0001", "提现金额不能为空! "),

    DRAWALSTYPE("0002", "提现账户类型不能为空! "),

     DRAWALSNULL("0003", "提现记录为空! "),

    RECORDIDNULL("0004", "记录Id不能为空! "),

    HANDLERSTATE("0005", "操作状态不能为空! "),

    DRAWAIDLSNULL("0007", "银行记录Id不能为空! "),

    DRAWLSINFOERR("0008", "您添加的银行卡账户开户名与实名认证姓名不符! "),

    BALANCENULL("0006", "账户余额不足! ");

    private String code;
    private String message;
    private String ns = "COM.SHIP.DRAWALS";

    DrawalsMess(String code,String message){
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
