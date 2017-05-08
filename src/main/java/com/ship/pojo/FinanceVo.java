package com.ship.pojo;


/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/10.
 */
public class FinanceVo {

    /**
     * 一级
     */
    private Double gradeone;
    /**
     *二级
     */
    private Double gradetwo;
    /**
     *三级
     */
    private Double gradethree;

    /**
     *账户余额
     */
    private Double balance;

    /**
     * 已提现金额
     */
    private Double already;

    /**
     * 用户累计总金额
     */
    private Double tatalMoney;

    /**
     * 提现成功金额
     */
    private Double drawalMoney;

    /**
     *等级总计
     */
    private Double gradeCount;



    public  FinanceVo(){}


    public Double getGradeone() {
        return gradeone;
    }

    public void setGradeone(Double gradeone) {
        this.gradeone = gradeone;
    }

    public Double getGradetwo() {
        return gradetwo;
    }

    public void setGradetwo(Double gradetwo) {
        this.gradetwo = gradetwo;
    }

    public Double getGradethree() {
        return gradethree;
    }

    public void setGradethree(Double gradethree) {
        this.gradethree = gradethree;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getAlready() {
        return already;
    }

    public void setAlready(Double already) {
        this.already = already;
    }


    public Double getTatalMoney() {
        return tatalMoney;
    }

    public void setTatalMoney(Double tatalMoney) {
        this.tatalMoney = tatalMoney;
    }


    public Double getDrawalMoney() {
        return drawalMoney;
    }

    public void setDrawalMoney(Double drawalMoney) {
        this.drawalMoney = drawalMoney;
    }

    public Double getGradeCount() {
        return gradeCount;
    }

    public void setGradeCount(Double gradeCount) {
        this.gradeCount = gradeCount;
    }
}





