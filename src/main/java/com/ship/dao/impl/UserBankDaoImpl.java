package com.ship.dao.impl;
import com.ship.common.jpa.BaseDaoImpl;
import com.ship.dao.UserBankDao;
import com.ship.domain.UserBank;
import com.ship.pojo.request.BankInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/7.
 */
public class UserBankDaoImpl extends BaseDaoImpl<UserBank> implements UserBankDao {

    @Override
    public UserBank getUserAccountInfo(Long userId) {
        String sql = "userId = ?";
        List<Object> rlist = new ArrayList<Object>();
        rlist.add(userId);
        return super.getEntityData(sql,rlist.toArray());
    }
}
