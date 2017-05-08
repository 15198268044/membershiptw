package com.ship.rest.manage;
import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.ship.common.util.*;
import com.ship.domain.BankInfo;
import com.ship.domain.UserBank;
import com.ship.domain.VipUser;
import com.ship.handler.BankInfoHandler;
import com.ship.handler.UserBankHandler;
import com.ship.handler.VipUserHandler;
import com.ship.pojo.ModifyUserVo;
import com.ship.pojo.error.SysError;
import com.ship.pojo.error.UserMess;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/14.
 * 修改用户信息
 */
@Path("admin/modify")
@Produces(MediaType.APPLICATION_JSON)
public class ModifyVipController {

    @Inject
    private VipUserHandler vipUserHandler;

    @Inject
    private UserBankHandler userBankHandler;

    @Inject
    private BankInfoHandler bankInfoHandler;

    /**
     * 获取会员信息
     * @param userId
     * @return
     */
    @GET
    @Path("getVipInfo.do")
    public String getVipInfo(@QueryParam("userId") Long userId){
        if (StringUtil.isEmpty(userId)){
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        try{
            ModifyUserVo userVo = new ModifyUserVo();
            VipUser vu =  vipUserHandler.find(userId);
            if (vu == null){
                return BaseResponse.of(Status.ERROR, UserMess.USERNULL.getCode(), UserMess.USERNULL.getMessage());
            }else{
                 UserBank ub =  userBankHandler.getUserAccountInfo(userId);

                 userVo.setUserId(vu.getId());
                 userVo.setHeadUrl(Param.getValue("img")+vu.getHeadUrl());
                 userVo.setRealname(vu.getRealname());
                 userVo.setReferrercode(vu.getReferrercode());
                 userVo.setPhone(vu.getPhone());
                 userVo.setVipname(vu.getVipname());
                 userVo.setIdcard(vu.getIdcard());
                 userVo.setIndirectId(vu.getIndirectId());
                 userVo.setIslock(vu.getIslock());
                 // TODO: ------------------------------------------------------------
                if (ub != null){
                        userVo.setbId(ub.getId());
                        userVo.setBankId(ub.getBankId());
                        userVo.setType(ub.getType());
                        userVo.setBanknum(ub.getBanknum());
                        BankInfo bi = bankInfoHandler.find(ub.getBankId());
                        if (bi == null){
                            userVo.setBankTypeName("");
                        }else{
                            userVo.setBankTypeName(bi.getBankname());
                        }
                        userVo.setAlipayAccount(ub.getAlipayAccount());
                        userVo.setBankaccountname(ub.getBankaccountname()==null?"":ub.getBankaccountname());
                        userVo.setAddress(ub.getAddress()==null?"":ub.getAddress());
                    }

            }
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),userVo);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }


    /**
     *
     * 修改用户信息
     * @param userInfo
     * @return
     */
    @POST
    @Path("modifyUserInfo.do")
    public  String  modifyUserInfo(@FormParam("userInfo") String userInfo){
        ModifyUserVo modify =  JSONObject.parseObject(userInfo,ModifyUserVo.class);
        if (StringUtil.isEmpty(modify.getUserId())){
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }

        try{
            VipUser vu =  vipUserHandler.find(modify.getUserId());

            if (StringUtil.isNotEmpty(modify.getPassword())){
                vu.setPassword(Encryption.MD5(modify.getPassword()));
            }

            if (StringUtil.isEmpty(modify.getReferrercode())){

                VipUser vus =  vipUserHandler.getUserPhone(modify.getReferrercode());
                if (vus == null){
                    return BaseResponse.of(Status.ERROR, UserMess.REFERCODENOTNULL.getCode(), UserMess.REFERCODENOTNULL.getMessage());
                }

                vu.setReferrercode(modify.getReferrercode());
            }

            if (StringUtil.isNotEmpty(modify.getPhone())){
                vu.setPhone(modify.getPhone());
            }

            if (StringUtil.isNotEmpty(modify.getVipname())){
                vu.setVipname(modify.getVipname());
            }

            if (StringUtil.isNotEmpty(modify.getRealname())){
                vu.setRealname(modify.getRealname());
            }

            if (StringUtil.isNotEmpty(modify.getIslock())){
                vu.setIslock(modify.getIslock());
            }

            vipUserHandler.update(vu);
            if (StringUtil.isEmpty(modify.getbId())){
                com.ship.pojo.request.BankInfo bio = new com.ship.pojo.request.BankInfo();
                bio.setBankId(modify.getBankId());
                bio.setAddress(modify.getAddress());
                bio.setBanknum(modify.getBanknum());
                bio.setAlipayAccount(modify.getAlipayAccount());
                bio.setBankaccountname(modify.getBankaccountname());
                bio.setBankType(modify.getType());
                userBankHandler.saveBank(bio,modify.getUserId());
                return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrMessage());
            }
            // TODO: ------------------------------------------------------------
            UserBank ub =  userBankHandler.find(modify.getbId());
            if (StringUtil.isNotEmpty(modify.getBankId())){
                ub.setBankId(modify.getBankId());
            }

            if (StringUtil.isNotEmpty(modify.getBankaccountname())){
                ub.setBankaccountname(modify.getBankaccountname());
            }

            if (StringUtil.isNotEmpty(modify.getBanknum())){
                ub.setBanknum(modify.getBanknum());
            }

            if (StringUtil.isNotEmpty(modify.getAddress())){
                ub.setAddress(modify.getAddress());
            }

            if (StringUtil.isNotEmpty(modify.getType())){
                ub.setType(modify.getType());
            }

            if (StringUtil.isNotEmpty(modify.getAlipayAccount())){
                ub.setAlipayAccount(modify.getAlipayAccount());
            }
            userBankHandler.update(ub);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrMessage());
    }












}
