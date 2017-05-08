package com.ship.rest.web;

import com.google.inject.Inject;
import com.ship.common.util.*;
import com.ship.domain.UserBank;
import com.ship.handler.*;
import com.ship.pojo.error.SysError;
import com.ship.pojo.error.UserMess;
import com.ship.pojo.FinanceVo;
import com.ship.pojo.SummaryVo;
import com.ship.domain.SysParam;
import com.ship.domain.VipUser;
import com.ship.domain.enums.ParamKey;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/9.
 * 资金汇总
 */
@Path("web/summary")
@Produces(MediaType.APPLICATION_JSON)
public class FundSummaryRest {

    @Inject
    private VipUserHandler vipUserHandler;

    @Inject
    private SysParamHandler sysParamHandler;

    @Inject
    private WithDrawalsHandler withDrawalsHandler;

    @Inject
    private UserBankHandler userBankHandler;

    @Inject
    private BankInfoHandler bankInfoHandler;

    /**
     * 用户财务统计
     * @param userId  用户Id
     * @return
     */
    @GET
    @Path("getFinance.do")
    public String getFinance(@QueryParam("userId") Integer userId){
        if (StringUtil.isEmpty(userId)){
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        try{
            VipUser user =  vipUserHandler.find(userId);
            FinanceVo financeVo = new FinanceVo();
            financeVo.setGradeone(user.getGradeone());
            financeVo.setGradetwo(user.getGradetwo());
            financeVo.setGradethree(user.getGradethree());
            financeVo.setBalance(user.getBalance());
            financeVo.setAlready(user.getAlready());
            financeVo.setTatalMoney(user.getTotal());
            financeVo.setDrawalMoney(withDrawalsHandler.getDrawalMoney());
            financeVo.setGradeCount(user.getGradeone()+user.getGradetwo()+user.getGradethree());
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),financeVo);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }

    /**
     * 用资金汇总
     * @param userId
     * @return
     */
    @GET
    @Path("getSummary.do")
    public String getSummary(@QueryParam("userId") Long userId){
        if (StringUtil.isEmpty(userId)) {
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        try{
            VipUser vipUser =  vipUserHandler.find(userId);
            if (vipUser == null){
                return BaseResponse.of(Status.ERROR, UserMess.PHONENUAALL.getMessage(),UserMess.PHONENUAALL.getCode());
            }else{
                SysParam par = sysParamHandler.getSysParam(ParamKey.USERGRADE.getContent());
                SummaryVo svo = new SummaryVo();
                svo.setFeeMoney(Integer.parseInt(par.getJson()));
                svo.setAlready(vipUser.getAlready()==null?0:vipUser.getAlready());
                svo.setBepresent(vipUser.getBalance()==null?0:vipUser.getBalance());
                svo.setHeadUrl(Param.getValue("img")+vipUser.getHeadUrl());
                svo.setVipName(vipUser.getVipname());
                svo.setPhone(StringUtil.hideTel(vipUser.getPhone()));
                UserBank user =  userBankHandler.getUserAccountInfo(vipUser.getId());
                if (user == null){
                    svo.setStatus(StateCode.NUMTWO);
                }else{
                    if (StringUtil.isEmpty(user.getAlipayAccount())){
                        svo.setStatus(StateCode.NUMONE);
                        svo.setBanknum(user.getBanknum());
                        svo.setBankId(user.getId());
                        svo.setBankTypeName(bankInfoHandler.find(user.getBankId()).getBankname());
                    }else if (StringUtil.isEmpty(user.getAddress())){
                        svo.setStatus(StateCode.NUMThree);
                        svo.setBanknum(user.getAlipayAccount());
                        svo.setBankId(user.getId());
                    }else{
                        svo.setStatus(3);
                        svo.setBankId(user.getId());
                        svo.setBankTypeName(bankInfoHandler.find(user.getBankId()).getBankname()+"--"+user.getBanknum());
                        svo.setBanknum(user.getAlipayAccount());
                    }
                }
                return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),svo);
            }
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }

}
