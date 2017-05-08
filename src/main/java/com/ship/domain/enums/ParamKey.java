package com.ship.domain.enums;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 * 参数Key
 */
public enum ParamKey {



    USERGRADE(1,"USERGRADE"),

    CAPITAL(3,"CAPITAL"),

    COUNTERFEE(2,"COUNTERFEE");

    private int code;

    private String content;

    ParamKey(int code, String content){
        this.code = code;
        this.content = content;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }





}
