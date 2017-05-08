package com.ship.pojo;


/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/11.
 * 银行账户信息
 */
public class BankInfoVo {
    /**
     *主键
     */
    private Long id;
    /**
     *银行账户
     */
    private String bankname;
    /**
     *状态（0：禁用，1：启用）
     */
    private Integer status;
    /**
     *添加时间
     */
    private String addTime;

    public BankInfoVo(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
