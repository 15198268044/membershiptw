package com.ship.pojo.request;


/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/6.
 * 用户注册信息
 */
public class VipUserReq {

    /**
     *手机号
     */
    private String phone;
    /**
     *用户名称
     */
    private String vipname;
    /**
     *推荐人账户
     */
    private String referrercode;
    /**
     *密码
     */
    private String password;
    /**
     *身份证号
     */
    private String idcard;
    /**
     * 真实名称
     */
    private String realname;

    /**
     *绑定邮箱
     */
    private String email;


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


    public String getReferrercode() {
        return referrercode;
    }

    public void setReferrercode(String referrercode) {
        this.referrercode = referrercode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
