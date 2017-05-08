package com.ship.common.util;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/10.
 */
public class SMSTemplent {

    /**
     *  注册
     * @param code
     * @return
     */
    public static String regTemplent(String code){
        return "【皇家平台】尊敬的会员您好，您的注册验证码是:"+code+"，请不要将验证码告诉他人。";
    }

    /**
     * 找回密码
     * @param code
     * @return
     */
    public static String forgotTemplent(String code){
        return "【皇家平台】尊敬的会员您好，您的找回密码验证码是:"+code+"，请不要将验证码告诉他人。";
    }
    /**
     * 修改密码
     * @param code
     * @return
     */
    public static String modifyPassTemplent(String code){
        return "【皇家平台】尊敬的会员您好，您的修改密码验证码是:"+code+"，请不要将验证码告诉他人。";
    }

    /**
     * 修改绑定手机
     * @param code
     * @return
     */
    public static String modifyBindTelTemplent(String code){
        return "【皇家平台】尊敬的会员您好，您的修改绑定手机验证码是:"+code+"，请不要将验证码告诉他人。";
    }

}
