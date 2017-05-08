package com.ship.rest.manage;
import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.ship.domain.SysParam;
import com.ship.domain.enums.CapitalTotal;
import com.ship.domain.enums.ParamKey;
import com.ship.handler.SysParamHandler;
import com.ship.pojo.DrawalsStateVo;
import com.ship.pojo.WithDrawalVo;
import com.ship.pojo.error.DrawalsMess;
import com.ship.pojo.error.SysError;
import com.ship.common.util.*;
import com.ship.handler.BranchRecordHandler;
import com.ship.handler.VipUserHandler;
import com.ship.handler.WithDrawalsHandler;
import com.ship.domain.VipUser;
import com.ship.domain.WithDrawals;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.POST;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 后台提现记录
 */
@Path("admin/drawals")
@Produces(MediaType.APPLICATION_JSON)
public class WithDrawalsController {

    @Inject
    private WithDrawalsHandler withDrawalsHandler;

    @Inject
    private BranchRecordHandler branchRecordHandler;

    @Inject
    private VipUserHandler vipUserHandler;

    @Inject
    private SysParamHandler sysParamHandler;

    /**
     * 后台获取提现记录
     * @param pageIndex     页码
     * @param pageSize      页大小
     * @param findvalue  流水号
     * @param bankId        银行账户id
     * @param state         状态
     * @param startDateTime 开始时间
     * @param endDateTime   结束时间
     * @return
     *
     *
     *
     */
    @GET
    @Path("getDrawalsList.do")
    public String getDrawalsList(
            @QueryParam("pageIndex") Integer pageIndex,
            @QueryParam("pageSize") Integer pageSize,
            @QueryParam("findvalue") String findvalue,
            @QueryParam("findtype") Integer findtype,
            @QueryParam("bankId") Long bankId,
            @QueryParam("state") Integer state,
            @QueryParam("time") Integer time,
            @QueryParam("startDateTime") Date startDateTime,
            @QueryParam("endDateTime") Date endDateTime
        ){
        try{
            Page<WithDrawalVo> page = null;
            if (StringUtil.isEmpty(pageIndex) || StringUtil.isEmpty(pageSize)){
                page = new Page<WithDrawalVo>(Param.pageIndex(),Param.pageSize());
            }else{
                page = new Page<WithDrawalVo>(pageIndex,pageSize);
            }
            FindParam param = new FindParam();

            if (StringUtil.isNotEmpty(findtype)){
                if (findtype == 0){
                    param.setSerialnumber(findvalue);
                }else if (findtype == 1){
                    param.setBankAccount(findvalue);
                }else if (findtype == 2){
                    param.setUsername(findvalue);
                }else{
                    param.setTelphone(findvalue);
                }
            }

            if (StringUtil.isNotEmpty(bankId)){
                param.setBankId(bankId);
            }
            if (StringUtil.isNotEmpty(state)){
                param.setState(state);
            }
            if (StringUtil.isNotEmpty(time)){
                param.setStartDateTime(DateUtil.dayTime(time));
            }
            if (StringUtil.isNotEmpty(startDateTime) && StringUtil.isNotEmpty(endDateTime)){
                param.setStartDateTime(startDateTime);
                param.setEndDateTime(endDateTime);
            }
            DrawalsStateVo stateVo =  withDrawalsHandler.getDrawalsStateMoney();
            SysParam sp =  sysParamHandler.getSysParam(ParamKey.CAPITAL.getContent());
            CapitalTotal ct = JSONObject.parseObject(sp.getJson(),CapitalTotal.class);
            stateVo.setFour(ct.getOne());
            Page<WithDrawalVo> pageView =  withDrawalsHandler.getUserDrawals(param,page);
            pageView.setDrawalsStateVo(stateVo);
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),pageView);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }

    /**
     * 资金提现处理
     * @param recordId  记录Id
     * @param state     操作状态
     * @return ，       1：银行处理中，2：提现成功，3：提现失败
     */
    @POST
    @Path("drawalsHandler.do")
    public String drawalsHandler(@FormParam("recordId") Long recordId,@FormParam("state") Integer state,@FormParam("mark") String mark){
        if (StringUtil.isEmpty(recordId)){
            return BaseResponse.of(Status.ERROR, DrawalsMess.RECORDIDNULL.getCode(),DrawalsMess.RECORDIDNULL.getMessage());
        }
        if (StringUtil.isEmpty(state)){
            return BaseResponse.of(Status.ERROR, DrawalsMess.HANDLERSTATE.getCode(),DrawalsMess.HANDLERSTATE.getMessage());
        }
        try{
            WithDrawals drawals = withDrawalsHandler.find(recordId);
            if (drawals == null){
                return BaseResponse.of(Status.ERROR, DrawalsMess.DRAWALSNULL.getCode(),DrawalsMess.DRAWALSNULL.getMessage());
            }else{
                VipUser vipUser =  vipUserHandler.find(drawals.getUserId());
                if (state == 3){
                    //失败
                    vipUser.setBalance(vipUser.getBalance() + drawals.getMoney());
                }else{
                    //成功
                    vipUser.setAlready(vipUser.getAlready() + drawals.getTatalmoney());
                }
                vipUserHandler.update(vipUser);
            }
            drawals.setStatus(state);
            drawals.setMark(mark);
            drawals.setHandlerTime(DateUtil.getDate());
            withDrawalsHandler.update(drawals);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }
}
