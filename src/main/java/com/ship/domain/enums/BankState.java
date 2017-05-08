package com.ship.domain.enums;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 * 银行账户类型
 */
public enum BankState {


    DISABLE(0,"禁用"),

    ENABLE(1,"启用");

    private int code;

    private String content;

    BankState(int code, String content){
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
