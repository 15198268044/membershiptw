package com.ship.pojo.request;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/20.
 * 银行账户信息
 */
public class BankInfo {

    /**
     * 银行账户（0：银联，1：支付宝）
     */
    private Integer bankType;
    /**
     * 银行类型id
     */
    private Long bankId;
    /**
     * 银行卡号
     */
    private String banknum;
    /**
     * 银行账户名称
     */
    private String bankaccountname;
    /**
     *支行地址
     */
    private String address;
    /**
     * 支付宝账户
     */
    private String alipayAccount;
    /**
     *
     */
    private int islock;

    public BankInfo(){

    }


    public Integer getBankType() {
        return bankType;
    }

    public void setBankType(Integer bankType) {
        this.bankType = bankType;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBanknum() {
        return banknum;
    }

    public void setBanknum(String banknum) {
        this.banknum = banknum;
    }

    public String getBankaccountname() {
        return bankaccountname;
    }

    public void setBankaccountname(String bankaccountname) {
        this.bankaccountname = bankaccountname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public int getIslock() {
        return islock;
    }

    public void setIslock(int islock) {
        this.islock = islock;
    }
}
