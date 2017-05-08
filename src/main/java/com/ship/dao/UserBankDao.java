package com.ship.dao;

import com.ship.common.jpa.BaseDao;
import com.ship.domain.UserBank;
import com.ship.pojo.request.BankInfo;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/7.
 * 用户提现账户Dao
 */
public interface UserBankDao extends BaseDao<UserBank> {
    /**
     * 获取银行账户信息
     * @param userId
     * @return
     */
    public UserBank getUserAccountInfo(Long userId);
}
