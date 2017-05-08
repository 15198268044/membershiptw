package com.ship.rest.web;
import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.ship.pojo.error.SysError;
import com.ship.pojo.error.UserMess;
import com.ship.common.util.*;
import com.ship.handler.VipUserHandler;
import com.ship.pojo.request.VipUserReq;
import com.ship.domain.VipUser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 */
@Path("web/vipuser")
@Produces(MediaType.APPLICATION_JSON)
public class VipUserRest {

    @Inject
    private VipUserHandler vipUserHandler;

    @Context
    private HttpServletRequest request;

    /**
     * vip用户注册
     * @param vipuserjson
     * @return
     */
    @POST
    @Path("vipRegister.do")
    public String vipRegister(@FormParam("vipuserjson")String  vipuserjson){
        VipUserReq vu = JSONObject.parseObject(vipuserjson,VipUserReq.class);
        if (StringUtil.isEmpty(vu.getPhone())){
            return BaseResponse.of(Status.ERROR, UserMess.PHONENULL.getCode(), UserMess.PHONENULL.getMessage());
        }
        if (StringUtil.isEmpty(vu.getVipname())){
            return BaseResponse.of(Status.ERROR, UserMess.VIPNAMENULL.getCode(), UserMess.VIPNAMENULL.getMessage());
        }
        if (StringUtil.isEmpty(vu.getIdcard())){
            return BaseResponse.of(Status.ERROR, UserMess.IDCARDNULL.getCode(), UserMess.IDCARDNULL.getMessage());
        }
        if (StringUtil.isEmpty(vu.getPassword())){
            return BaseResponse.of(Status.ERROR, UserMess.PASSWORDNULL.getCode(), UserMess.PASSWORDNULL.getMessage());
        }
        if (StringUtil.isEmpty(vu.getRealname())){
            return BaseResponse.of(Status.ERROR, UserMess.REALNAMENULL.getCode(), UserMess.REALNAMENULL.getMessage());
        }
        try{
            return  vipUserHandler.vipRegister(vu,StateCode.NUMTWO);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }

    /**
     * 绑定邮箱
     * @param userId
     * @param email
     * @return
     */
    @POST
    @Path("bindEmail.do")
    public String bindEmail(@FormParam("userId") Long userId,@FormParam("email") String email){
        if (StringUtil.isEmpty(email)){
            return BaseResponse.of(Status.ERROR, UserMess.PASSWORDNULL.getCode(), UserMess.PASSWORDNULL.getMessage());
        }
        try{
            VipUser v =  vipUserHandler.find(userId);
            v.setEmail(email);
            vipUserHandler.update(v);
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }

    /**
     * 手机号输入提示
     * @param account
     * @return
     */
    @GET
    @Path("lenovoAccount.do")
    public String lenovoAccount(@QueryParam("account") String account){
        if (StringUtil.isEmpty(account)){
            return BaseResponse.of(Status.ERROR, UserMess.ACCOUNTNULL.getCode(), UserMess.ACCOUNTNULL.getMessage());
        }
        try{
            List<String> rlist =  vipUserHandler.lenovoAccount(account);
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),rlist);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }


    /**
     * 退出登录
     * @param
     * @return
     */
    @GET
    @Path("exitLogin.do")
    public String exitLogin(){
        UserInfo.remove();
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }


    /**
     * 验证手机用户是否存在
     * @param types  0:手机号， 1：用户名
     * @param account 账号
     * @return
     */
    @GET
    @Path("checkOnly.do")
    public String checkOnly(@QueryParam("types") Integer types,@QueryParam("account") String account){
        try{
            if (types == 0){
                VipUser vs =  vipUserHandler.getUserPhone(account);
                if (vs == null){
                    return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
                }
            }
            if (types == 1){
                VipUser vs =    vipUserHandler.getVipName(account);
                if (vs == null){
                    return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.ERROR, UserMess.VIPNAMENOTNULL.getMessage(),UserMess.VIPNAMENOTNULL.getCode());
    }

    /**
     * 用户登录
     * @param  account
     * @param  password
     * @return
     */
    @POST
    @Path("userlogin.do")
    public String userlogin(@FormParam("account") String account,@FormParam("password") String password){
        if (StringUtil.isEmpty(account)){
            return BaseResponse.of(Status.ERROR, UserMess.ACCOUNTNULL.getCode(), UserMess.ACCOUNTNULL.getMessage());
        }
        if (StringUtil.isEmpty(password)){
            return BaseResponse.of(Status.ERROR, UserMess.PASSWORDNULL.getCode(), UserMess.PASSWORDNULL.getMessage());
        }
        try{
              VipUser vipUser = vipUserHandler.userlogin(account,Encryption.MD5(password));
              if (vipUser == null){
                 return   BaseResponse.of(Status.ERROR, UserMess.ACCOUNTORPASSERROR.getCode(), UserMess.ACCOUNTORPASSERROR.getMessage());
              }else{
                  if (vipUser.getIslock() == StateCode.NUMThree){
                      return BaseResponse.of(Status.ERROR, UserMess.ISLOCK.getCode(), UserMess.ISLOCK.getMessage());
                  }
                  vipUser.setLoginTime(DateUtil.getTimestamp());
                  vipUser.setLoginIp(WebUtil.getIpaddr());
                  vipUser.setLoginnum(vipUser.getLoginnum()+1);
                  vipUserHandler.update(vipUser);
                  Map<String,Object> map = new HashMap<String,Object>();
                  map.put("userId",vipUser.getId());
                  map.put("vipname",vipUser.getVipname());
                  map.put("phone",vipUser.getPhone());
                  map.put("email",vipUser.getEmail() == null?"":vipUser.getEmail());
                  request.getSession().setAttribute("userId",vipUser.getId());
                  UserInfo.setId(vipUser.getId());
                 return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),map);
            }
        }catch (Exception e){
          e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }
}