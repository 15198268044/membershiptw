package com.ship.pojo;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/7.
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WithDrawalVo {

    /**
     * 主键
     */
    private  Long id;
    /**
     *申请时间
     */
    private String applyTime;
    /**
     *流水号
     */
    private String serialnumber;
    /**
     *处理时间
     */
    private String handlerTime;
    /**
     *手续费
     */
    private Double counterFee;
    /**
     *提现金额
     */
    private Double money;
    /**
     *状态
     */
    private String status;
    /**
     *类型
     */
    private Integer type;
    /**
     *备注
     */
    private String mark;
    /**
     * 提现总金额
     */
    private Double tatalmoney;

    /**
     * 银行账户信息
     */
    private  BankInfoRequest bankInfo;

    /**
     *电话
     */
    private String phone;
    /**
     *真实名称
     */
    private String realname;



    public WithDrawalVo(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getHandlerTime() {
        return handlerTime;
    }

    public void setHandlerTime(String handlerTime) {
        this.handlerTime = handlerTime;
    }

    public Double getCounterFee() {
        return counterFee;
    }

    public void setCounterFee(Double counterFee) {
        this.counterFee = counterFee;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public BankInfoRequest getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfoRequest bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }


    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Double getTatalmoney() {
        return tatalmoney;
    }

    public void setTatalmoney(Double tatalmoney) {
        this.tatalmoney = tatalmoney;
    }

    public  static class BankInfoRequest{

        /**
         *开户地址
         */
        private String address;
        /**
         *开户名称
         */
        private String bankaccountname;
        /**
         *银行账户
         */
        private String banknum;
        /**
         *银行名称
         */
        private String bankname;

        public BankInfoRequest(){

        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBankaccountname() {
            return bankaccountname;
        }

        public void setBankaccountname(String bankaccountname) {
            this.bankaccountname = bankaccountname;
        }

        public String getBanknum() {
            return banknum;
        }

        public void setBanknum(String banknum) {
            this.banknum = banknum;
        }

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

    }

}

