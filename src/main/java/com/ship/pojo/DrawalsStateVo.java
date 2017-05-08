package com.ship.pojo;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/11.
 * 提现状态金额
 */
public class DrawalsStateVo {


    /**
     *待提现金额
     */
    private Double one = 0.0;
    /**
     *  处理中金额
     */
    private Double two  = 0.0;;
    /**
     * 已提现金额
     */
    private Double three  = 0.0;;
    /**
     *分润总金额
     */
    private Double four  = 0.0;;


    public DrawalsStateVo(){}


    public Double getOne() {
        return one;
    }

    public void setOne(Double one) {
        this.one = one;
    }

    public Double getTwo() {
        return two;
    }

    public void setTwo(Double two) {
        this.two = two;
    }

    public Double getThree() {
        return three;
    }

    public void setThree(Double three) {
        this.three = three;
    }

    public Double getFour() {
        return four;
    }

    public void setFour(Double four) {
        this.four = four;
    }
}
