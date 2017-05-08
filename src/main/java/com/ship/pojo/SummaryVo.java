package com.ship.pojo;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/9.
 * 资金汇总 数据返回
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SummaryVo {

    /**
     *头像
     */
    private String headUrl;
    /**
     *用户名
     */
    private String vipName;
    /**
     *手机号
     */
    private String phone;
    /**
     *已提现金额
     */
    private Double already;
    /**
     *待提现金额
     */
    private Double bepresent;
    /**
     * 手续费
     */
    private Integer feeMoney;
    /**
     *
     */
    private Integer status;

    private Integer drawType;
    /**
     *
     */
    private String banknum;
    /**
     *银行类型名称
     */
    private String bankTypeName;
    private Long bankId;


    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getAlready() {
        return already;
    }

    public void setAlready(Double already) {
        this.already = already;
    }

    public Double getBepresent() {
        return bepresent;
    }

    public void setBepresent(Double bepresent) {
        this.bepresent = bepresent;
    }

    public Integer getFeeMoney() {
        return feeMoney;
    }

    public void setFeeMoney(Integer feeMoney) {
        this.feeMoney = feeMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDrawType() {
        return drawType;
    }

    public void setDrawType(Integer drawType) {
        this.drawType = drawType;
    }

    public String getBanknum() {
        return banknum;
    }

    public void setBanknum(String banknum) {
        this.banknum = banknum;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }


    public String getBankTypeName() {
        return bankTypeName;
    }

    public void setBankTypeName(String bankTypeName) {
        this.bankTypeName = bankTypeName;
    }
}
