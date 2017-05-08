package com.ship.dao.impl;
import com.ship.common.jpa.BaseDaoImpl;
import com.ship.dao.AdminDao;
import com.ship.domain.DbAdmin;
import java.util.List;
import java.util.ArrayList;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/1.
 * AdminDao 实现
 */
public class AdminDaoImpl  extends BaseDaoImpl<DbAdmin> implements AdminDao {

    @Override
    public DbAdmin login(String account, String password) {
        List<Object> param = new ArrayList<Object>();
        param.add(account);
        param.add(password);
        String sql = "account = ? and password = ?";
        return  super.getEntityData(sql,param.toArray());
    }

}
