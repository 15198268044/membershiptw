package com.ship.rest.web;

import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.ship.common.util.BaseResponse;
import com.ship.common.util.Status;
import com.ship.common.util.StringUtil;
import com.ship.domain.UserBank;
import com.ship.domain.VipUser;
import com.ship.handler.UserBankHandler;
import com.ship.handler.VipUserHandler;
import com.ship.pojo.error.*;
import com.ship.pojo.request.BankInfo;
import sun.text.normalizer.UBiDiProps;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/7.
 * 用户提现账户操作
 */
@Path("web/userbank")
@Produces(MediaType.APPLICATION_JSON)
public class UserBankRest {

    @Inject
    private UserBankHandler userBankHandler;

    @Inject
    private VipUserHandler vipUserHandler;

    /**
     * 修改提现账户信息
     * @param userId
     * @param bankInfo
     * @return
     */
    @POST
    @Path("upUserBank.do")
    public String upUserBank(@FormParam("userId")Long userId, @FormParam("bankInfo") String bankInfo){
        BankInfo bi = JSONObject.parseObject(bankInfo,BankInfo.class);
        if (StringUtil.isEmpty(userId)){
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        try{
            UserBank ub =  userBankHandler.getUserAccountInfo(userId);
            if (ub == null){
              String mess  =    userBankHandler.saveBank(bi,userId);
              if (StringUtil.isNotEmpty(mess)){
                  return BaseResponse.of(Status.ERROR, BankMess.ACCOUNTERROR.getCode(), BankMess.ACCOUNTERROR.getMessage());
              }
            }else{
                ub.setType(bi.getBankType());
                VipUser user =  vipUserHandler.find(userId);
                if (!user.getRealname().equals(bi.getBankaccountname())){
                    return BaseResponse.of(Status.ERROR, DrawalsMess.DRAWLSINFOERR.getCode(), DrawalsMess.DRAWLSINFOERR.getMessage());
                }
                if(bi.getBankType() == 0){
                    ub.setBankId(bi.getBankId());
                    ub.setBanknum(bi.getBanknum());
                    ub.setAddress(bi.getAddress());
                    ub.setBankaccountname(bi.getBankaccountname());
                }else{
                    ub.setAlipayAccount(bi.getBanknum());
                }
                userBankHandler.update(ub);
            }
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SmsMess.CHECKSUCCESS.getCode(),SmsMess.CHECKSUCCESS.getMessage());
    }


}
