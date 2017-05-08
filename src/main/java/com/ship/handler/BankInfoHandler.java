package com.ship.handler;

import com.ship.common.jpa.BaseServer;
import com.ship.domain.BankInfo;
import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 银行信息处理
 */
public interface BankInfoHandler extends BaseServer<BankInfo>{


    /**
     * 获取银行账户列表
     * @return
     */
    public List<BankInfo> getBankList(Integer mark);




}
