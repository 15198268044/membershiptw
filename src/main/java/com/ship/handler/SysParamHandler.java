package com.ship.handler;

import com.ship.common.jpa.BaseServer;
import com.ship.domain.SysParam;

import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 系统参数处理
 * */
public interface SysParamHandler extends BaseServer<SysParam> {


    /**
     * 根据参数key获取参数
     * @param parKey
     * @return
     */
    SysParam getSysParam(String parKey);

    /**
     * 获取全部系统参数
     * @return
     */
    List<SysParam> getParamList();

}
