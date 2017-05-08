package com.ship.dao;

import com.ship.common.jpa.BaseDao;
import com.ship.domain.DbAdmin;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/1.
 * 管理员Dao
 */
public interface AdminDao extends BaseDao<DbAdmin> {
    /**
     * 管理员登录
     * @param account
     * @param password
     * @return
     */
    public DbAdmin  login(String account,String password);

}
