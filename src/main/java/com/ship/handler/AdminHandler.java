package com.ship.handler;

import com.ship.common.jpa.BaseServer;
import com.ship.domain.DbAdmin;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/1.
 * 管理员服务接口
 */
public interface AdminHandler  extends BaseServer<DbAdmin>{

    /**
     * 管理员登录
     * @param account
     * @param password
     * @return
     */
    public DbAdmin  login(String account,String password);


}
