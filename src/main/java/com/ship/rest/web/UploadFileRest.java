package com.ship.rest.web;

import com.google.inject.Inject;
import com.ship.common.util.BaseResponse;
import com.ship.common.util.Status;
import com.ship.common.util.StringUtil;
import com.ship.common.util.UploadUtil;
import com.ship.domain.VipUser;
import com.ship.handler.VipUserHandler;
import com.ship.pojo.error.SuaryMess;
import com.ship.pojo.error.SysError;
import com.ship.pojo.error.UserMess;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/7.
 * 用户头像上传
 */
@Path("web/uploadfile")
@Produces(MediaType.APPLICATION_JSON)
public class UploadFileRest {

    @Inject
    private VipUserHandler vipUserHandler;

    /**
     * 获取用户头像
     * @param userId
     * @return
     */
    @GET
    @Path("getUserHead.do")
    public String getUserHead(@QueryParam("userId") Long userId) {
        if (StringUtil.isEmpty(userId)) {
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        try {
            VipUser user = vipUserHandler.find(userId);
            if (user == null) {
                return BaseResponse.of(Status.ERROR, UserMess.USERNULL.getCode(), UserMess.USERNULL.getMessage());
            }
            Map<String, String> mData = new HashMap<String, String>();
            System.out.print(user.getHeadUrl());
            mData.put("headUrl", user.getHeadUrl() == null ? "" : user.getHeadUrl());
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(), SysError.SUCCESS.getErrCode(),mData);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }


}
