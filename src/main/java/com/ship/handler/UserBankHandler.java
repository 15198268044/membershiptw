package com.ship.handler;

import com.ship.common.jpa.BaseServer;
import com.ship.domain.UserBank;
import com.ship.pojo.request.BankInfo;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/14.
 * 用户银行账户处理
 * */
public interface UserBankHandler extends BaseServer<UserBank> {

    /**
     * 保存银行账户
     * @param userBank
     */
    public String saveBank(BankInfo userBank,Long userId);

    /**
     * 获取银行账户信息
     * @param userId
     * @return
     */
    public UserBank getUserAccountInfo(Long userId);



}
