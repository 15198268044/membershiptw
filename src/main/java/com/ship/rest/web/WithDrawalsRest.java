package com.ship.rest.web;
import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.ship.domain.VipUser;
import com.ship.handler.VipUserHandler;
import com.ship.pojo.error.DrawalsMess;
import com.ship.pojo.error.SysError;
import com.ship.pojo.error.UserMess;
import com.ship.common.util.BaseResponse;
import com.ship.common.util.StringUtil;
import com.ship.common.util.Status;
import com.ship.common.util.Param;
import com.ship.common.util.FindParam;
import com.ship.common.util.Page;
import com.ship.common.util.DateUtil;
import com.ship.pojo.WithDrawalVo;
import com.ship.pojo.request.DrawlsRequest;
import com.ship.handler.WithDrawalsHandler;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 */
@Path("web/drawals")
@Produces(MediaType.APPLICATION_JSON)
public class WithDrawalsRest {

    @Inject
    private WithDrawalsHandler withDrawalsHandler;

    @Inject
    private VipUserHandler vipUserHandler;

    /**
     * 提现申请
     * @param jsonData
     * @return
     */
    @POST
    @Path("withDrawalsApply.do")
    public String withDrawalsApply(@FormParam("jsonData") String jsonData){
        DrawlsRequest drawls = JSONObject.parseObject(jsonData,DrawlsRequest.class);
        if (StringUtil.isEmpty(drawls.getUserId())) {
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        if (StringUtil.isEmpty(drawls.getTatalmoney())) {
            return BaseResponse.of(Status.ERROR, DrawalsMess.TATALMONEYNULL.getCode(),DrawalsMess.TATALMONEYNULL.getMessage());
        }
        if (StringUtil.isEmpty(drawls.getType())) {
            return BaseResponse.of(Status.ERROR, DrawalsMess.DRAWALSTYPE.getCode(),DrawalsMess.DRAWALSTYPE.getMessage());
        }
        try{
            VipUser vipUser =  vipUserHandler.find(drawls.getUserId());
            if (vipUser.getBalance() < drawls.getTatalmoney()){
                return BaseResponse.of(Status.ERROR, DrawalsMess.BALANCENULL.getCode(),DrawalsMess.BALANCENULL.getMessage());
            }
            //更新用户余额
            vipUser.setBalance(vipUser.getBalance() - drawls.getTatalmoney());
            vipUserHandler.update(vipUser);
            return  withDrawalsHandler.withDrawalsApply(drawls);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }

    /**
     *
     * 用户提现记录
     * @param userId        用户Id
     * @param pageIndex     页码
     * @param pageSize      页大小
     * @param state         状态
     * @param time          时间
     * @param startTimeDate 开始时间
     * @param endTimeDate   结束时间
     * @return
     */
    @GET
    @Path("getUserDrawals.do")
    public String getUserDrawals(@QueryParam("userId") Long userId,@QueryParam("pageIndex") Integer pageIndex,@QueryParam("pageSize") Integer pageSize,@QueryParam("state") Integer state,@QueryParam("time") Integer time,@QueryParam("startTimeDate") Date startTimeDate,@QueryParam("endTimeDate") Date endTimeDate) {
        if (StringUtil.isEmpty(userId)) {
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        Page<WithDrawalVo> page = null;
        if (StringUtil.isEmpty(pageIndex) || StringUtil.isEmpty(pageSize)) {
            page = new Page<WithDrawalVo>(Param.pageIndex(), Param.pageSize());
        } else {
            page = new Page<WithDrawalVo>(pageIndex, pageSize);
        }
        try {
            FindParam drawals = new FindParam();
            drawals.setUserId(userId);
            if (StringUtil.isNotEmpty(state)) {
                drawals.setState(state);
            }
            if (StringUtil.isNotEmpty(time)) {
                drawals.setStartDateTime(DateUtil.dayTime(time));
            }
            if (StringUtil.isNotEmpty(startTimeDate) && StringUtil.isNotEmpty(endTimeDate)) {
                drawals.setStartDateTime(startTimeDate);
                drawals.setEndDateTime(endTimeDate);
            }
            Page<WithDrawalVo> pageV = withDrawalsHandler.getUserDrawals(drawals, page);
            if (page.getRecords() == null || page.getRecords().size() == 0) {
                return BaseResponse.of(Status.ERROR, DrawalsMess.DRAWALSNULL.getCode(), DrawalsMess.DRAWALSNULL.getMessage());
            } else {
                return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(), SysError.SUCCESS.getErrCode(), pageV);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }
}