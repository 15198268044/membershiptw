package com.ship.pojo;

import com.ship.common.util.StringUtil;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 * 修改用户信息
 */
public class ModifyUserVo {

    /**
     *主键
     */
    private Long userId;
    /**
     *推荐人Id
     */
    private String referrercode;
    /**
     *手机号
     */
    private String phone;
    /**
     *用户名称
     */
    private String vipname;

    /**
     * 是否锁定(0:正常，1：分红冻结，2：禁止登录 )
     */
    private Integer islock;
    /**
     *密码
     */
    private String password;
    /**
     *头像URL
     */
    private String headUrl;
    /**
     *简接推荐Id
     */
    private String indirectId;

    /**
     *身份证号
     */
    private String idcard;

    /**
     * 真实名称
     */
    private String realname;

    // TODO: -------------------------------华丽分割线 -----------------------------------
    /**
     *
     */
    private  Long bId;

    /**
     * 类型ID
     */
    private Long bankId;
    /**
     * 0:银联，1：支付宝
     */
    private Integer type;
    /**
     * 账户
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
     * 银行类型名称
     */
    private String bankTypeName;


    public ModifyUserVo(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReferrercode() {
        return referrercode;
    }

    public void setReferrercode(String referrercode) {
        this.referrercode = referrercode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVipname() {
        return vipname;
    }

    public void setVipname(String vipname) {
        this.vipname = vipname;
    }

    public Integer getIslock() {
        return islock;
    }

    public void setIslock(Integer islock) {
        this.islock = islock;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getIndirectId() {
        return indirectId;
    }

    public void setIndirectId(String indirectId) {
        this.indirectId = indirectId;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
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

    public String getBankTypeName() {
        return bankTypeName;
    }

    public void setBankTypeName(String bankTypeName) {
        this.bankTypeName = bankTypeName;
    }
}
