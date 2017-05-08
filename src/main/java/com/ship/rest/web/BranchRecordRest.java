package com.ship.rest.web;

import com.google.inject.Inject;
import com.ship.pojo.error.SuaryMess;
import com.ship.pojo.error.SysError;
import com.ship.pojo.error.UserMess;
import com.ship.common.util.*;
import com.ship.handler.BranchRecordHandler;
import com.ship.domain.BranchRecord;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 分润
 */
@Path("web/branch")
@Produces(MediaType.APPLICATION_JSON)
public class BranchRecordRest {

    @Inject
    private BranchRecordHandler branchRecordHandler;

    /**
     * 我的分润记录
     * @param userId        用户Id
     * @param serialnumber  流水号
     * @param type          类型
     * @param pageIndex     页码
     * @param pageSize      页大小
     * @param startDateTime 开始时间
     * @param endDateTime   结束时间
     * @return
     */
    @GET
    @Path("getBranchList.do")
    public String getBranchList(@QueryParam("userId")Long userId,@QueryParam("serialnumber")String serialnumber,
                                @QueryParam("type")Integer type,@QueryParam("pageIndex")Integer pageIndex,
                                @QueryParam("time")Integer time,
                                @QueryParam("pageSize")Integer pageSize,@QueryParam("startDateTime")Date startDateTime,
                                @QueryParam("endDateTime")Date endDateTime
                                ){
        if (StringUtil.isEmpty(userId)){
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        Page<BranchRecord> pageVo = null;
        if (StringUtil.isEmpty(pageIndex) || StringUtil.isEmpty(pageSize)){
            pageVo = new Page<BranchRecord>(Param.pageIndex(),Param.pageSize());
        }else{
            pageVo = new Page<BranchRecord>(pageIndex,pageSize);
        }
        try{
            FindParam  param = new FindParam();
            param.setUserId(userId);
            if (StringUtil.isNotEmpty(serialnumber)){
                param.setSerialnumber(serialnumber);
            }
            if (StringUtil.isNotEmpty(type)){
                param.setState(type);
            }

            if (StringUtil.isNotEmpty(time)){
                param.setStartDateTime(DateUtil.dayTime(time));
            }

            if (StringUtil.isNotEmpty(startDateTime) && StringUtil.isNotEmpty(endDateTime)){
                param.setStartDateTime(startDateTime);
                param.setEndDateTime(endDateTime);
            }
            Page<BranchRecord> voPage = branchRecordHandler.getBranchList(param,pageVo);
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),voPage);
        }catch(Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }

    /**
     * 获取记录详情
     * @param id
     * @return
     */
    @GET
    @Path("getBranchDetails.do")
    public String getBranchDetails(@QueryParam("id")Long id){
        if (StringUtil.isEmpty(id)){
            return BaseResponse.of(Status.ERROR, SuaryMess.RECORDIDNULL.getCode(), SuaryMess.RECORDIDNULL.getMessage());
        }
        try{
            BranchRecord record = branchRecordHandler.find(id);
            record.setDateTime(record.getAddTime().toString());
            switch (record.getGrade()){
                case 1 :
                    record.setUsergrade("一级用户");
                 break;
                case 2:
                    record.setUsergrade("二级用户");
                    break;
                case 3 :
                    record.setUsergrade("三级用户");
                    break;
                default:
                    record.setUsergrade("业务结算");
            }
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),record);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }
}
