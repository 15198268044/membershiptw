package com.ship.pojo;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 * 银行账户信息响应
 */
public class BankVo {

    /**
     * id
     */
    private  Long id;
    /**
     * 银行名称
     */
    private  String  bankName;


    public  BankVo(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
