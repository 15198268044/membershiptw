package com.ship.rest.manage;

import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.ship.handler.UserBankHandler;
import com.ship.pojo.error.SmsMess;
import com.ship.pojo.error.SuaryMess;
import com.ship.pojo.error.SysError;
import com.ship.pojo.error.UserMess;
import com.ship.common.util.*;
import com.ship.pojo.UserVo;
import com.ship.pojo.request.BankInfo;
import com.ship.pojo.request.VipUserReq;
import com.ship.handler.VipUserHandler;
import com.ship.domain.VipUser;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 */
@Path("admin/vipuser")
@Produces(MediaType.APPLICATION_JSON)
public class VipUserController {

    @Inject
    private VipUserHandler vipUserHandler;


    @Inject
    private UserBankHandler userBankHandler;


    /**
     * 获取用户信息
     * @param username
     * @param state
     * @param startTimeDate
     * @param endTimeDate
     * @return
     */
    @GET
    @Path("getVipUser.do")
    public String getVipUser(@QueryParam("username") String username,
                             @QueryParam("state") Integer state,
                             @QueryParam("pageIndex") Integer pageIndex,
                             @QueryParam("pageSize") Integer pageSize,
                             @QueryParam("startTimeDate") Date startTimeDate,
                             @QueryParam("endTimeDate") Date endTimeDate){
        try{
            FindParam param = new FindParam();
            Page<UserVo> page = null;
            if (StringUtil.isEmpty(pageIndex) || StringUtil.isEmpty(pageSize)){
                page = new Page<UserVo>(Param.pageIndex(),Param.pageSize());
            }else{
                page = new Page<UserVo>(pageIndex,pageSize);
            }
            if (StringUtil.isNotEmpty(username)){
                param.setUsername(username);
            }
            if (StringUtil.isNotEmpty(state)){
                param.setState(state);
            }
            if (StringUtil.isNotEmpty(startTimeDate) && StringUtil.isNotEmpty(endTimeDate)) {
                param.setStartDateTime(startTimeDate);
                param.setEndDateTime(endTimeDate);
            }
            Page<UserVo> userPage  =  vipUserHandler.getVipUser(param,page);
            if (userPage.getRecords() == null ||userPage.getRecords().size() == 0){
                return BaseResponse.of(Status.ERROR,UserMess.USERDATANULL.getCode(), UserMess.USERDATANULL.getMessage());
            }else{
                return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),userPage);
            }
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @GET
    @Path("deleteUser.do")
    public String deleteUser(@QueryParam("userId") Long userId){
        if (StringUtil.isEmpty(userId)){
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        try{
            vipUserHandler.delete(userId);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }

    /**
     * 修改状态锁
     * @param userId
     * @param state  是否锁定(0:正常，1：冻结，2：禁止登录 )
     * @return
     */
    @GET
    @Path("modifyState.do")
    public String modifyState(@QueryParam("userId") Long userId,@QueryParam("state") Integer state){
        if (StringUtil.isEmpty(userId)){
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        if (StringUtil.isEmpty(state)){
            return BaseResponse.of(Status.ERROR, UserMess.OLDPHONERROR.getCode(), UserMess.OLDPHONERROR.getMessage());
        }
        if (!(state == 0  || state ==1 || state ==2)){
            return BaseResponse.of(Status.ERROR, SmsMess.SMSTYPERANGE.getCode(),SmsMess.SMSTYPERANGE.getMessage());
        }
        try{
            VipUser user =  vipUserHandler.find(userId);
            user.setIslock(state);
            vipUserHandler.update(user);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }

    /**
     * 后台添加用户
     * @param vipuserjson
     * @return
     */
    @POST
    @Path("addVipUser.do")
    public String addVipUser(@FormParam("vipuserjson")String  vipuserjson,@FormParam("bankinfo")String  bankinfo){
        VipUserReq vu = JSONObject.parseObject(vipuserjson,VipUserReq.class);
        BankInfo bankInfo =  JSONObject.parseObject(bankinfo,BankInfo.class);
        if (StringUtil.isEmpty(vu.getPhone())){
            return BaseResponse.of(Status.ERROR, UserMess.PHONENULL.getCode(), UserMess.PHONENULL.getMessage());
        }
        if (StringUtil.isEmpty(vu.getVipname())){
            return BaseResponse.of(Status.ERROR, UserMess.VIPNAMENULL.getCode(), UserMess.VIPNAMENULL.getMessage());
        }
        if (StringUtil.isEmpty(vu.getPassword())){
            return BaseResponse.of(Status.ERROR, UserMess.PASSWORDNULL.getCode(), UserMess.PASSWORDNULL.getMessage());
        }
        if (StringUtil.isEmpty(vu.getReferrercode())){
            vu.setReferrercode("0");
        }
        try{
              String str  =  vipUserHandler.vipRegister(vu,bankInfo.getIslock());
              JSONObject obj =  JSONObject.parseObject(str);
              JSONObject data =  obj.getJSONObject("date");
              Long s =  data.getLong("userId");
              if (StringUtil.isNotEmpty(bankInfo.getAlipayAccount())){
                  bankInfo.setBankaccountname(vu.getRealname());
              }
              userBankHandler.saveBank(bankInfo,s);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }
}
