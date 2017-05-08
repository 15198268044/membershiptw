package com.ship.dao;

import com.ship.common.jpa.BaseDao;
import com.ship.common.util.FindParam;
import com.ship.common.util.Page;
import com.ship.domain.BranchRecord;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 分润Dao
 */
public interface BranchRecordDao extends BaseDao<BranchRecord>{

    /**
     * 获取用户分润记录
     * @param param
     * @param page
     * @return
     */
    public Page<BranchRecord> getBranchList(FindParam param, Page<BranchRecord> page);


}
