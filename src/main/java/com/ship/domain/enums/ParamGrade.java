package com.ship.domain.enums;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 * 参数登录
 */
public class ParamGrade {

    /**
     * 一级
     */
    private Integer one;
    /**
     *二级
     */
    private Integer two;
    /**
     *三级
     */
    private Integer three;

    public ParamGrade(){

    }

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
}
