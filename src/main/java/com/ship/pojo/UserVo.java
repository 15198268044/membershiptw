package com.ship.pojo;

/**
 * 用户Vo
 */
public class UserVo {
    /**
     *主键
     */
    private Long userId;
    /**
     *登录时间
     */
    private String loginDate;
    /**
     * 推荐人
     */
    private String referrercode;
    /**
     *登录时间
     */
    private String phone;
    /**
     *推荐人
     */
    private String refername;
    /**
     *被推荐人
     */
    private String diname;
    /**
     *推荐人数
     */
    private Integer pernum;
    /**
     *总业绩
     */
    private Double total;
    /**
     *头像URL
     */
    private String headUrl;

    /**
     * 真实名称
     */
    private String realname;
    /**
     *是否锁定(0:锁定，1：未锁定)
     */
    private Integer islock;

    /**
     *登录Ip
     */
    private String loginIp;

    /**
     * 登录次数
     */
    private Integer loginnum;

    /**
     *用户名称
     */
    private String vipname;

    public UserVo(){


    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getRefername() {
        return refername;
    }

    public void setRefername(String refername) {
        this.refername = refername;
    }

    public String getDiname() {
        return diname;
    }

    public void setDiname(String diname) {
        this.diname = diname;
    }

    public Integer getPernum() {
        return pernum;
    }

    public void setPernum(Integer pernum) {
        this.pernum = pernum;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getIslock() {
        return islock;
    }

    public void setIslock(Integer islock) {
        this.islock = islock;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Integer getLoginnum() {
        return loginnum;
    }

    public void setLoginnum(Integer loginnum) {
        this.loginnum = loginnum;
    }

    public String getVipname() {
        return vipname;
    }

    public void setVipname(String vipname) {
        this.vipname = vipname;
    }

    public String getReferrercode() {
        return referrercode;
    }

    public void setReferrercode(String referrercode) {
        this.referrercode = referrercode;
    }
}
