package com.ship.pojo.error;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/9.
 */
public enum  SuaryMess {


    REFERBNULL("0001", "推荐人不存在！ "),
    RECORDIDNULL("0002", "记录Id不能为空！ "),
    UPLOADERR("0003", "上传失败")
    ;


    private String code;
    private String message;
    private String ns = "COM.SHIP.SUMMARY";

    SuaryMess(String code,String message){
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
