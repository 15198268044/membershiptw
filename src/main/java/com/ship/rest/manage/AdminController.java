package com.ship.rest.manage;

import com.google.inject.Inject;
import com.ship.pojo.error.AdminMess;
import com.ship.pojo.error.SysError;
import com.ship.common.util.*;
import com.ship.pojo.AdminVo;
import com.ship.handler.AdminHandler;
import com.ship.domain.DbAdmin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/3/31.
 * 管理员操作
 */
@Path("admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminController {

    @Inject
    private AdminHandler adminHandler;

    /**
     * @param account   账号
     * @param password  密码
     * @return
     */
    @POST
    @Path("login.do")
    public String login(@FormParam("account") String account, @FormParam("password") String password){
        if (StringUtil.isEmpty(account)){
            return BaseResponse.of(Status.ERROR,AdminMess.ACCOUNTNULL.getCode(),AdminMess.ACCOUNTNULL.getMessage());
        }
        if (StringUtil.isEmpty(password)){
             return BaseResponse.of(Status.ERROR,AdminMess.PASSWRODNULL.getCode(),AdminMess.PASSWRODNULL.getMessage());
        }
        try{
            DbAdmin admin =  adminHandler.login(account,Encryption.MD5(password));
            if (admin == null){
                return BaseResponse.of(Status.ERROR, AdminMess.ACCPASSERROR.getCode(),AdminMess.ACCPASSERROR.getMessage());
            }else{
                //更新登录信息
                admin.setLoginTime(DateUtil.getTimestamp());
                admin.setLoginIp(WebUtil.getIpaddr());
                admin.setLoginnum(admin.getLoginnum() + 1);
                adminHandler.update(admin);
                AdminVo adminVo = new AdminVo();
                adminVo.setAccount(admin.getAccount());
                adminVo.setLoginIp(admin.getLoginIp());
                adminVo.setLoginnum(admin.getLoginnum());
                adminVo.setLoginTime(admin.getLoginTime().toString());
                return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),adminVo);
            }
        }catch (Exception e){
            e.printStackTrace();
          return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }
}
