package com.ship.pojo;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 */
public class ParamVo {
    /**
     *等级一
     */
    private  Integer one;
    /**
     *等级二
     */
    private  Integer two;
    /**
     *等级三
     */
    private  Integer three;
    /**
     *手续费
     */
    private  Integer fee;

    public Integer getOne() {
        return one;
    }

    public void setOne(Integer one) {
        this.one = one;
    }

    public Integer getTwo() {
        return two;
    }

    public void setTwo(Integer two) {
        this.two = two;
    }

    public Integer getThree() {
        return three;
    }

    public void setThree(Integer three) {
        this.three = three;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
}
