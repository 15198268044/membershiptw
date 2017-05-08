package com.ship.rest.web;
import com.google.inject.Inject;
import com.ship.domain.VipUser;
import com.ship.handler.VipUserHandler;
import com.ship.pojo.error.SmsMess;
import com.ship.pojo.error.SysError;
import com.ship.pojo.error.UserMess;
import com.ship.common.util.*;
import com.ship.handler.SmsCodeHander;
import com.ship.domain.SmsCode;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 */
@Path("web/sms")
@Produces(MediaType.APPLICATION_JSON)
public class SmsCodeRest {

    @Inject
    private SmsCodeHander smsCodeHander;

    /**
     * 发送短信验证码
     * @param phone  手机号
     * @param type   验证码类型 0:注册，1：找回密码，2：修改密码,3:修改绑定手机
     * @return
     */
    @POST
    @Path("sendSms.do")
    public String sendSms(@FormParam("phone") String phone, @FormParam("type") Integer type){
        if (StringUtil.isEmpty(phone)){
            return BaseResponse.of(Status.ERROR, UserMess.PHONENULL.getCode(),UserMess.PHONENULL.getMessage());
        }
        if (StringUtil.isEmpty(type)) {
            return BaseResponse.of(Status.ERROR, SmsMess.SMSTYPE.getCode(),SmsMess.SMSTYPE.getMessage());
        }
        if (!(type >= 0 || type <= 3 )){
            return BaseResponse.of(Status.ERROR, SmsMess.SMSTYPERANGE.getCode(),SmsMess.SMSTYPE.getMessage());
        }
        try{
            // 生成随机验证码
            String checkCode =  NumberUtil.rand();
            SmsCode sms = new  SmsCode();
            sms.setCode(checkCode);
            sms.setType(type);
            sms.setAddTime(DateUtil.getMillisecond());
            sms.setPhone(phone);
            SmsCode smscode =  smsCodeHander.isPhoneSms(phone);
            if (smscode == null) {
                smsCodeHander.save(sms);
            }else{
                if (smsTimeValid(smscode.getAddTime()).equals("0")){
                    return BaseResponse.of(Status.ERROR, SmsMess.SMSCODEC.getCode(),SmsMess.SMSCODEC.getMessage());
                }
                smscode.setType(type);
                smscode.setCode(checkCode);
                smscode.setAddTime(DateUtil.getMillisecond());
                smsCodeHander.update(smscode);
            }
             SendSMS.sendSmsCode(type,phone,checkCode);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }

    /**
     * 发送验证码到邮箱
     * @param email
     * @return
     */
    @POST
    @Path("sendEmail.do")
    public String sendEmail(@FormParam("email") String email){
        if (StringUtil.isEmpty(email)) {
            return BaseResponse.of(Status.ERROR, SmsMess.EMAILNULL.getCode(), SmsMess.EMAILNULL.getMessage());
        }
        try{
            String checkCode =  NumberUtil.rand();
            // 生成随机验证码
            SmsCode sms = new  SmsCode();
            sms.setCode(checkCode);
            sms.setType(4);
            sms.setAddTime(DateUtil.getMillisecond());
            sms.setPhone(email);
            SmsCode smscode =  smsCodeHander.isPhoneSms(email);
            if (smscode == null) {
                smsCodeHander.save(sms);
            }else{
                smscode.setType(4);
                smscode.setCode(checkCode);
                smscode.setAddTime(DateUtil.getMillisecond());
                smsCodeHander.update(smscode);
            }
            SendCommonPostMail.sendEmail(email,checkCode);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }

    /**
     * 验证验证码
     * @param phone  账户
     * @param code   验证码
     * @return
     */
    @POST
    @Path("checkSms.do")
    public String checkSms(@FormParam("phone") String phone, @FormParam("code") String code) {
        if (StringUtil.isEmpty(phone)) {
            return BaseResponse.of(Status.ERROR, UserMess.PHONENULL.getCode(),UserMess.PHONENULL.getMessage());
        }
        if (StringUtil.isEmpty(code)) {
            return BaseResponse.of(Status.ERROR, SmsMess.SMSCODENULL.getCode(),SmsMess.SMSCODENULL.getMessage());
        }
        try {
            SmsCode smscode =  smsCodeHander.isPhoneSms(phone);
            if (!smscode.getCode().equals(code)) {
                return BaseResponse.of(Status.ERROR, SmsMess.SMSCODEERROR.getCode(),SmsMess.SMSCODEERROR.getMessage());
            }
            // 判读验证码是否有效
            if (smsValid(smscode.getAddTime()).equals("00")) {
                return BaseResponse.of(Status.ERROR, SmsMess.SMSCODEEXPIRED.getCode(),SmsMess.SMSCODEEXPIRED.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }

    @Context
    private HttpServletRequest request;

    /**
     * 获取验证码
     * @return
     */
    @POST
    @Path("getGraphicalSms.do")
    public String getGraphicalSms(){
        try {
            String sms =  CheckCode.getSms();
            request.getSession().setAttribute("smscode",sms);
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),sms);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }

    /**
     *
     * 检查验证码
     * @param smscode
     * @return
     */
    @POST
    @Path("checkGraphical.do")
    public String checkGraphical(@FormParam("smscode") String smscode){
        if (StringUtil.isEmpty(smscode)){
            return BaseResponse.of(Status.ERROR, SmsMess.SMSCODENULL.getCode(),SmsMess.SMSCODENULL.getMessage());
        }
        try{
            String sc= String.valueOf(request.getSession().getAttribute("smscode"));
            if (sc.equalsIgnoreCase(smscode)){
                return BaseResponse.of(Status.SUCCESS, SmsMess.CHECKSUCCESS.getCode(),SmsMess.CHECKSUCCESS.getMessage());
            }else{
                return BaseResponse.of(Status.ERROR, SmsMess.CHECKERROR.getCode(),SmsMess.CHECKERROR.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }

    /**
     * 验证验证码是否过期
     * @param addTime
     * @return
     */
    public static String smsValid(Long addTime) {
        Long nowdate = DateUtil.getMillisecond();
        long xc = (nowdate - addTime) / 1000 / 60; // 时间相差分钟数
        if (xc >= 20) {
            return "00";
        }
        return "";
    }

    /**
     * 验证验证码是否过期
     * @param addTime
     * @return
     */
    public static String smsTimeValid(Long addTime) {
        Long nowdate = DateUtil.getMillisecond();
        long xc = (nowdate - addTime) / 1000 / 60; // 时间相差分钟数
        if (xc <= 2) {
            return "0";
        }
        return "";
    }
}
