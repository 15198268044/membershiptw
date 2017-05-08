package com.ship.domain.enums;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 * 参数登录
 */
public class CapitalTotal {

    /**
     * 一级
     */
    private Double one;
    /**
     *二级
     */
    private Double two;
    /**
     *三级
     */
    private Double three;

    public CapitalTotal(){

    }

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
}
