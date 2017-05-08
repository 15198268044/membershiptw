package com.ship.rest.manage;

import com.google.inject.Inject;
import com.ship.common.util.*;
import com.ship.pojo.BankInfoVo;
import com.ship.pojo.error.BankMess;
import com.ship.pojo.error.SysError;
import com.ship.handler.BankInfoHandler;
import com.ship.domain.BankInfo;
import com.ship.domain.enums.BankState;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 */
@Path("admin/bankInfo")
@Produces(MediaType.APPLICATION_JSON)
public class BankInfoContoller {

    @Inject
    private BankInfoHandler bankInfoHandler;

    /**
     * 添加银行账户
     * @param bankname
     * @return
     */
    @POST
    @Path("addBankAccount.do")
    public String addBankAccount(@FormParam("bankname") String bankname){
        if (StringUtil.isEmpty(bankname)){
            return BaseResponse.of(Status.ERROR, BankMess.BANKNAMENULL.getMessage(),BankMess.BANKNAMENULL.getCode());
        }
        try {
            BankInfo bankInfo = new BankInfo();
            bankInfo.setBankname(bankname);
            bankInfo.setAddTime(DateUtil.getDate());
            bankInfo.setStatus(BankState.ENABLE.getCode());
            bankInfoHandler.save(bankInfo);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }

    /**
     * 删除银行账户
     * @param bankId  银行账户
     * @return
     */
    @POST
    @Path("deleteBankAccount.do")
    public String deleteBankAccount(@FormParam("bankId") Long bankId){
        if (StringUtil.isEmpty(bankId)){
            return BaseResponse.of(Status.ERROR, BankMess.BANKIDNULL.getMessage(),BankMess.BANKIDNULL.getCode());
        }
        try{
            bankInfoHandler.delete(bankId);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }

    /**
     * 修改银行信息
     * @param bankId
     * @return
     */
    @POST
    @Path("bankState.do")
    public String bankState(@FormParam("bankId") Long bankId,@FormParam("bankname") String bankname,@FormParam("status") Integer status){
        if (StringUtil.isEmpty(bankId)){
            return BaseResponse.of(Status.ERROR, BankMess.BANKIDNULL.getMessage(),BankMess.BANKIDNULL.getCode());
        }
        try{
            BankInfo bankInfo =  bankInfoHandler.find(bankId);
            if (bankInfo == null){
                return BaseResponse.of(Status.ERROR, BankMess.BANKACCOUNTNULL.getMessage(),BankMess.BANKACCOUNTNULL.getCode());
            }else{
                if (StringUtil.isNotEmpty(bankname)){
                    bankInfo.setBankname(bankname);
                }
                if (StringUtil.isNotEmpty(status)){
                    bankInfo.setStatus(status);
                }
                bankInfoHandler.update(bankInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }

    /**
     * 获取银行账户列表
     * @return
     */
    @GET
    @Path("getBankList.do")
    public String getBankList(){
        try{
           List<BankInfo> bankInfoList =  bankInfoHandler.getBankList(StateCode.NUMTWO);
           if (bankInfoList == null || bankInfoList.size() == 0){
               return BaseResponse.of(Status.ERROR, BankMess.BANKACCOUNTNULL.getMessage(),BankMess.BANKACCOUNTNULL.getCode());
           }else{
               List<BankInfoVo> rList= new ArrayList<BankInfoVo>();
               for (BankInfo bi:bankInfoList) {
                   BankInfoVo vo = new BankInfoVo();
                   vo.setId(bi.getId());
                   vo.setBankname(bi.getBankname());
                   vo.setStatus(bi.getStatus());
                   vo.setAddTime(bi.getAddTime().toString());
                   rList.add(vo);
               }
               return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),rList);
           }

        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }

}
