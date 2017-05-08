package com.ship.dao;

import com.ship.common.jpa.BaseDao;
import com.ship.domain.BankInfo;

import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 银行信息Dao
 */
public interface BankInfoDao extends BaseDao<BankInfo> {


    /**
     * 获取银行账户列表
     * @return
     */
    public List<BankInfo> getBankList(Integer mark);

}
