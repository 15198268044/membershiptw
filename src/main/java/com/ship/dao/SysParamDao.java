package com.ship.dao;

import com.ship.common.jpa.BaseDao;
import com.ship.domain.SysParam;

import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 系统参数Dao
 */
public interface SysParamDao extends BaseDao<SysParam>{

    /**
     * 根据参数key获取参数
     * @param parKey
     * @return
     */
    public SysParam getSysParam(String parKey);

    /**
     * 获取全部系统参数
     * @return
     */
    public List<SysParam> getParamList();
}
