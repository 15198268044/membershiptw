package com.ship.handler;

import com.ship.common.jpa.BaseServer;
import com.ship.common.util.FindParam;
import com.ship.common.util.Page;
import com.ship.pojo.UserVo;
import com.ship.domain.VipUser;
import com.ship.pojo.request.VipUserReq;

import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 *  用户逻辑处理
 */
public interface VipUserHandler extends BaseServer<VipUser>{

    /**
     * 会员注册
     * @param vu
     * @return
     */
    public String vipRegister(VipUserReq vu, int mark);
    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    public VipUser userlogin(String account,String password);

    /**
     * 根据手机号获取用户
     * @param phone
     * @return
     */
    public VipUser getUserPhone(String phone);

    /**
     * 输入联系手机号
     * @param account
     * @return
     */
    public List<String> lenovoAccount(String account);

    /**
     * 查询给用户名是否 存在
     * @param vipname
     * @return
     */
    public VipUser getVipName(String vipname);

    /**
     * 获取推荐人
     * @param phone
     * @return
     */
    public VipUser getRefer(String phone);

    /**
     * 获取系统会员
     * @param param
     * @param page
     * @return
     */
    public Page<UserVo> getVipUser(FindParam param, Page<UserVo> page);



}
