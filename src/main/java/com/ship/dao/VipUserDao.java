package com.ship.dao;

import com.ship.common.jpa.BaseDao;
import com.ship.common.util.FindParam;
import com.ship.common.util.Page;
import com.ship.pojo.UserVo;
import com.ship.domain.VipUser;

import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 用户Dao
 */
public interface VipUserDao extends BaseDao<VipUser> {
    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    public VipUser userlogin(String account,String password);

    /**
     * 查询给用户名是否 存在
     * @param vipname
     * @return
     */
    public VipUser getVipName(String vipname);

    /**
     * 输入联系手机号
     * @param account
     * @return
     */
    public List<String> lenovoAccount(String account);
    /**
     * 获取推荐人
     * @param phone
     * @return
     */
    public VipUser getRefer(String phone);
    /**
     * 根据手机号获取用户
     * @param phone
     * @return
     */
    public VipUser getUserPhone(String phone);


    /**
     * 获取系统会员
     * @param param
     * @param page
     * @return
     */
    public Page<UserVo> getVipUser(FindParam param, Page<UserVo> page);


}
