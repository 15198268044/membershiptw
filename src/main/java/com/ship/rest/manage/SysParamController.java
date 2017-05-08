package com.ship.rest.manage;

import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.ship.pojo.error.ParamMess;
import com.ship.pojo.error.SysError;
import com.ship.common.util.BaseResponse;
import com.ship.common.util.Status;
import com.ship.common.util.StringUtil;
import com.ship.pojo.ParamVo;
import com.ship.handler.SysParamHandler;
import com.ship.domain.SysParam;
import com.ship.domain.enums.ParamGrade;
import com.ship.domain.enums.ParamKey;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 */
@Path("admin/sysparam")
@Produces(MediaType.APPLICATION_JSON)
public class SysParamController {

    @Inject
    private SysParamHandler sysParamHandler;


    /**
     * 修改手续费
     * @param cost
     * @return
     */
    @POST
    @Path("upparam.do")
    public String upFee(@FormParam("cost") String cost){
        if (StringUtil.isEmpty(cost)){
            return BaseResponse.of(Status.ERROR,ParamMess.COUNTERFEENULL.getCode(),ParamMess.COUNTERFEENULL.getMessage());
        }
        try{
            SysParam sysParam =    sysParamHandler.getSysParam(ParamKey.USERGRADE.getContent());
            sysParam.setJson(cost);
            sysParamHandler.update(sysParam);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }

    /**
     * 修改收分配比
     * @param costone
     * @param costtwo
     * @param costthree
     * @return
     */
    @POST
    @Path("upGrade.do")
    public String upGrade(@FormParam("costone") Integer costone,@FormParam("costtwo") Integer costtwo,@FormParam("costthree") Integer costthree){
        if (StringUtil.isEmpty(costone)){
            return BaseResponse.of(Status.ERROR,ParamMess.PARAMNULL.getCode(),ParamMess.PARAMNULL.getMessage());
        }
        if (StringUtil.isEmpty(costtwo)){
            return BaseResponse.of(Status.ERROR,ParamMess.PARAMNULL.getCode(),ParamMess.PARAMNULL.getMessage());
        }
        if (StringUtil.isEmpty(costthree)){
            return BaseResponse.of(Status.ERROR,ParamMess.PARAMNULL.getCode(),ParamMess.PARAMNULL.getMessage());
        }
        try{
            SysParam sysParam =    sysParamHandler.getSysParam(ParamKey.COUNTERFEE.getContent());
            ParamGrade grade =  JSONObject.parseObject(sysParam.getJson(),ParamGrade.class);
            grade.setOne(costone);
            grade.setTwo(costtwo);
            grade.setThree(costthree);
            sysParam.setJson(JSONObject.toJSONString(grade));
            sysParamHandler.update(sysParam);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }


    /**
     * 获取系统参数
     * @return
     */
    @GET
    @Path("getParamList.do")
    public String getParamList(){
        try{
            ParamVo paramVo = new ParamVo();
            SysParam user =  sysParamHandler.getSysParam(ParamKey.USERGRADE.getContent());
            paramVo.setFee(Integer.parseInt(user.getJson()));
            SysParam sys = sysParamHandler.getSysParam(ParamKey.COUNTERFEE.getContent());
            ParamGrade grade =  JSONObject.parseObject(sys.getJson(),ParamGrade.class);
            paramVo.setOne(grade.getOne());
            paramVo.setTwo(grade.getTwo());
            paramVo.setThree(grade.getThree());
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),paramVo);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }


}
