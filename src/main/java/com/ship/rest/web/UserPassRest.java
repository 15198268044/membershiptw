package com.ship.rest.web;
import com.google.inject.Inject;
import com.ship.pojo.error.SysError;
import com.ship.pojo.error.UserMess;
import com.ship.common.util.*;
import com.ship.handler.VipUserHandler;
import com.ship.domain.VipUser;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/7.
 * 用户密码操作
 */
@Path("web/pass")
@Produces(MediaType.APPLICATION_JSON)
public class UserPassRest {

    @Inject
    private VipUserHandler vipUserHandler;


    /**
     *  忘记密码
     * @param phone         手机号
     * @param password      密码
     * @return
     */
    @POST
    @Path("forgot.do")
    public String  forgotPassword(@FormParam("phone") String phone,@FormParam("password")  String password) {
        if (StringUtil.isEmpty(phone)) {
            return BaseResponse.of(Status.ERROR, UserMess.PHONENULL.getCode(), UserMess.PHONENULL.getMessage());
        }
        if (StringUtil.isEmpty(password)) {
            return BaseResponse.of(Status.ERROR, UserMess.PASSWORDNULL.getCode(), UserMess.PASSWORDNULL.getMessage());
        }
        try {
            VipUser user = vipUserHandler.getUserPhone(phone);
            if (user == null) {
                return BaseResponse.of(Status.ERROR, UserMess.PHONENUAALL.getCode(), UserMess.PHONENUAALL.getMessage());
            }
            if (user.getIslock() == StateCode.NUMThree) {
                return BaseResponse.of(Status.ERROR, UserMess.ISLOCK.getCode(), UserMess.ISLOCK.getMessage());
            }
            user.setPassword(Encryption.MD5(password));
            vipUserHandler.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }

    /**修改密码
     * @param userId    用户Id
     * @param oldpass   原密码
     * @param newpass   新密码
     * @return
     */
    @POST
    @Path("updatePass.do")
    public String modifyPassword(@FormParam("userId") Long userId,@FormParam("oldpass")  String oldpass,@FormParam("newpass")  String newpass) {
        if (StringUtil.isEmpty(userId)) {
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        if (StringUtil.isEmpty(oldpass)) {
            return BaseResponse.of(Status.ERROR, UserMess.OLDPASSNULL.getCode(), UserMess.OLDPASSNULL.getMessage());
        }
        if (StringUtil.isEmpty(newpass)) {
            return BaseResponse.of(Status.ERROR, UserMess.NEWPASSNULL.getCode(), UserMess.NEWPASSNULL.getMessage());
        }
        try {
            VipUser user =  vipUserHandler.find(userId);
            if (user == null) {
                return BaseResponse.of(Status.ERROR, UserMess.PHONENUAALL.getCode(), UserMess.PHONENUAALL.getMessage());
            }
            if (user.getIslock() == StateCode.NUMThree) {
                return BaseResponse.of(Status.ERROR, UserMess.ISLOCK.getCode(), UserMess.ISLOCK.getMessage());
            }
            if (!user.getPassword().equals(Encryption.MD5(oldpass))) {
                return BaseResponse.of(Status.ERROR, UserMess.OLDPASSERROR.getCode(), UserMess.OLDPASSERROR.getMessage());
            }
            user.setPassword(Encryption.MD5(newpass));
            vipUserHandler.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }


    /**
     * 修改绑定手机号码
     * @param userId  用户Id
     * @param oldTel  旧手机号
     * @param newTel  新手机号
     * @return
     */
    @POST
    @Path("modifyUserPhone.do")
    public String modifyUserPhone(@FormParam("userId")Long userId,@FormParam("oldTel") String oldTel,@FormParam("newTel") String newTel) {
        if (StringUtil.isEmpty(userId)) {
            return BaseResponse.of(Status.ERROR, UserMess.USERIDNULL.getCode(), UserMess.USERIDNULL.getMessage());
        }
        if (StringUtil.isEmpty(oldTel)) {
            return BaseResponse.of(Status.ERROR, UserMess.OLDTELNULL.getCode(), UserMess.OLDTELNULL.getMessage());
        }
        if (StringUtil.isEmpty(newTel)) {
            return BaseResponse.of(Status.ERROR, UserMess.NEWTELNULL.getCode(), UserMess.NEWTELNULL.getMessage());
        }
        try {
            VipUser user = vipUserHandler.getUserPhone(newTel);
            if (user != null) {
                return BaseResponse.of(Status.ERROR, UserMess.ACCOUNTTOREGI.getCode(), UserMess.ACCOUNTTOREGI.getMessage());
            }
            VipUser use = vipUserHandler.find(userId);
            if (use == null) {
                return BaseResponse.of(Status.ERROR, UserMess.PHONENUAALL.getCode(), UserMess.PHONENUAALL.getMessage());
            }
            use.setPhone(newTel);
            vipUserHandler.update(use);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
    }

}