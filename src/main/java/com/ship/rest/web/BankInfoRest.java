package com.ship.rest.web;

import com.google.inject.Inject;
import com.ship.common.util.StateCode;
import com.ship.pojo.error.BankMess;
import com.ship.pojo.error.SysError;
import com.ship.common.util.BaseResponse;
import com.ship.common.util.Status;
import com.ship.pojo.BankVo;
import com.ship.handler.BankInfoHandler;
import com.ship.domain.BankInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 */
@Path("web/bank")
@Produces(MediaType.APPLICATION_JSON)
public class BankInfoRest {

    @Inject
    private BankInfoHandler bankInfoHandler;


    @POST
    @Path("checkSign.do")
    public String checkSign(){
        System.out.print("---------------------------------------");
        return  "";
    }


    /**
     * 获取银行账户
     * @return
     */
    @GET
    @Path("getBankList.do")
    public String getBankList(){
        try{
            List<BankInfo> bankInfoList =  bankInfoHandler.getBankList(StateCode.NUMONE);
            if (bankInfoList == null|| bankInfoList.size() == 0){
                return BaseResponse.of(Status.ERROR, BankMess.BANKACCOUNTNULL.getMessage(),BankMess.BANKACCOUNTNULL.getCode());
            }else{
                List<BankVo> rlist = new ArrayList<BankVo>();
                for (BankInfo bi:bankInfoList) {
                    BankVo bankVo = new BankVo();
                    bankVo.setId(bi.getId());
                    bankVo.setBankName(bi.getBankname());
                    rlist.add(bankVo);
                }
                return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),rlist);
            }
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }

}
