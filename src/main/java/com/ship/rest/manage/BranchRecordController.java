package com.ship.rest.manage;

import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.ship.common.util.*;
import com.ship.domain.BranchRecord;
import com.ship.domain.SysParam;
import com.ship.domain.VipUser;
import com.ship.domain.enums.CapitalTotal;
import com.ship.domain.enums.ParamGrade;
import com.ship.domain.enums.ParamKey;
import com.ship.handler.SysParamHandler;
import com.ship.handler.VipUserHandler;
import com.ship.pojo.error.BranchMess;
import com.ship.pojo.error.SysError;
import com.ship.handler.BranchRecordHandler;
import com.ship.pojo.error.UserMess;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 分润
 */
@Path("admin/branch")
@Produces(MediaType.APPLICATION_JSON)
public class BranchRecordController {

    @Inject
    private BranchRecordHandler branchRecordHandler;

    @Inject
    private VipUserHandler vipUserHandler;

    @Inject
    private SysParamHandler sysParamHandler;

    /**
     * 获取分红等级
     * @param account
     * @return
     */
    @GET
    @Path("getGradeName.do")
    public String getGradeName(@QueryParam("account") String account){
        if (StringUtil.isEmpty(account)){
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        Map<String,String> map = new HashMap<String, String>();
        VipUser vipUser =  vipUserHandler.getUserPhone(account);
        if (vipUser == null){
            return BaseResponse.of(Status.ERROR, UserMess.USERNULL.getCode(), UserMess.USERNULL.getMessage());
        }
        VipUser refer =  vipUserHandler.getUserPhone(vipUser.getReferrercode());
        if (refer == null){
            return BaseResponse.of(Status.ERROR, BranchMess.ERRORACCOUNT.getCode(),BranchMess.ERRORACCOUNT.getMessage());
        }else{
            map.put("num","1");
            if (refer.getIslock() == StateCode.NUMONE){
                map.put("gradeoneName",refer.getRealname()+"(冻结中)");
                map.put("onevipname",refer.getVipname()+"(冻结中)");
            }else{
                map.put("gradeoneName",refer.getRealname());
                map.put("onevipname",refer.getVipname());
            }
            map.put("oneId",refer.getId().toString());
        }
        VipUser referttwo =  vipUserHandler.getUserPhone(refer.getReferrercode());
        if (referttwo == null){
            map.put("num","1");
            map.put("gradetwoName","无");
            map.put("gradethreeName","无");
            map.put("twovipname","无");
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),map);
        }else{
            map.put("num","2");
            if (refer.getIslock() == StateCode.NUMONE){
                map.put("gradetwoName",referttwo.getRealname()+"(冻结中)");
                map.put("twovipname",referttwo.getVipname()+"(冻结中)");
            }else{
                map.put("gradetwoName",referttwo.getRealname());
                map.put("twovipname",referttwo.getVipname());
            }
            map.put("twoId",referttwo.getId().toString());
        }
        VipUser referthree =  vipUserHandler.getUserPhone(referttwo.getReferrercode());
        if (referthree == null){
            map.put("num","2");
            map.put("gradethreeName","无");
            map.put("threevipname","无");
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),map);
        }else{
            map.put("num","3");
            if (refer.getIslock() == StateCode.NUMONE){
                map.put("gradethreeName",referthree.getRealname()+"(冻结中)");
                map.put("threevipname",referthree.getVipname()+"(冻结中)");
            }else{
                map.put("gradethreeName",referthree.getRealname());
                map.put("threevipname",referthree.getVipname());
            }
            map.put("threeId",referthree.getId().toString());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),map);
    }

    /**
     * 分润处理
     * @param tatalMoney  分润金额
     * @param account     账户
     * @return
     */
    @POST
    @Path("handlerBanch.do")
    public String handlerBranch(@FormParam("tatalMoney") Double tatalMoney,@FormParam("account") String account){
        if (StringUtil.isEmpty(tatalMoney)){
            return BaseResponse.of(Status.ERROR,  BranchMess.TATALMONEYNULL.getCode(),BranchMess.TATALMONEYNULL.getMessage());
        }
        if (StringUtil.isEmpty(account)){
            return BaseResponse.of(Status.ERROR,BranchMess.ACCOUNTNULL.getCode(),BranchMess.ACCOUNTNULL.getMessage());
        }
        try{
            SysParam param =  sysParamHandler.getSysParam(ParamKey.COUNTERFEE.getContent());
            ParamGrade grade = JSONObject.parseObject(param.getJson(),ParamGrade.class);
            VipUser  str =  vipUserHandler.getUserPhone(account);
            if (str == null){
                return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
            }
            VipUser vu = null;
            Double mons = 0.0;
            for(int i = 0;i<3;i++){
                Double mon = 0.0;
                if (i==0){
                    vu = vipUserHandler.getUserPhone(str.getReferrercode());
                    if (vu == null){
                        return CommonsMess.NEXTGRADE;
                    }
                    if (vu.getIslock() == StateCode.NUMONE){
                       continue;
                    }
                    mon =  NumberUtil.calc(tatalMoney,grade.getOne());
                    mons+=mon;
                    vu.setTotal(vu.getTotal()+mon);
                    vu.setGradeone(vu.getGradeone()+mon);
                    vu.setBalance(vu.getBalance() + mon);
                }else if (i==1){
                    vu = vipUserHandler.getUserPhone(vu.getReferrercode());
                    if (vu == null){
                        break;
                    }
                    if (vu.getIslock() == StateCode.NUMONE){
                        continue;
                    }
                    mon =  NumberUtil.calc(tatalMoney,grade.getTwo());
                    mons+=mon;
                    vu.setTotal(vu.getTotal()+mon);
                    vu.setGradetwo(vu.getGradetwo() + mon);
                    vu.setBalance(vu.getBalance() + mon);
                }else if (i==2){
                    vu = vipUserHandler.getUserPhone(vu.getReferrercode());
                    if (vu == null){
                        break;
                    }
                    if (vu.getIslock() == StateCode.NUMONE){
                        continue;
                    }
                    mon =  NumberUtil.calc(tatalMoney,grade.getThree());
                    mons+=mon;
                    vu.setTotal(vu.getTotal()+mon);
                    vu.setGradethree(vu.getGradethree() + mon);
                    vu.setBalance(vu.getBalance() + mon);
                }

                vipUserHandler.update(vu);
                saveBrankRecord(tatalMoney,vu.getId(),vu.getRealname(),grade.getOne(),mon);
            }

            // TODO 累计分配金额
            SysParam sp =  sysParamHandler.getSysParam(ParamKey.CAPITAL.getContent());
            CapitalTotal capital = JSONObject.parseObject(sp.getJson(),CapitalTotal.class);
            capital.setOne(capital.getOne() + tatalMoney);
            capital.setTwo(capital.getTwo()+mons);
            sp.setJson(JSONObject.toJSONString(capital));
            sysParamHandler.update(sp);

        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }



    /**
     * 保存分润记录
     * @param tatal     分配金额
     * @param vipid     会员Id
     * @param vipname   会员名称
     * @param grade     等级
     * @param actual    实际金额
     */
    public void saveBrankRecord(double tatal,long vipid,String vipname,Integer grade,Double actual){
        BranchRecord record = new BranchRecord();
        record.setTatalmoney(tatal);
        record.setVipId(vipid);
        record.setRealname(vipname);
        record.setSerialnumber(StringUtil.getSerial());
        record.setMark("--");
        record.setGrade(grade);
        record.setActualmoney(actual);
        record.setAddTime(DateUtil.getDate());
        branchRecordHandler.save(record);
    }

}
