package com.ship.pojo.error;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 * 参数提示信息
 */
public enum ParamMess {



    COUNTERFEENULL("0001", "手续费不能为空! "),
    PARAMNULL("0002", "参数为空! ")

    ;



    private String code;
    private String message;
    private String ns = "COM.SHIP.PARAM";

    ParamMess(String code,String message){
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
