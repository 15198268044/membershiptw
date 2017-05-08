package com.ship.pojo.request;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/7.
 */
public class DrawlsRequest {

    /**
     * 用户Id
     */
    private Long userId;
    /**
     *提现总金额
     */
    private Double tatalmoney;
    /**
     *银行账户
     */
    private Long bankId;
    /**
     * 0:银联，1：支付宝
     */
    private Integer type;



    public DrawlsRequest(){


    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getTatalmoney() {
        return tatalmoney;
    }

    public void setTatalmoney(Double tatalmoney) {
        this.tatalmoney = tatalmoney;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }


    public Integer getType() {
        return type;
    }


    public void setType(Integer type) {
        this.type = type;
    }


}
