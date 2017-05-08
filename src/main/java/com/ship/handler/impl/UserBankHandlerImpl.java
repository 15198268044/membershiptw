package com.ship.handler.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.ship.common.util.DateUtil;
import com.ship.common.util.StringUtil;
import com.ship.dao.UserBankDao;
import com.ship.dao.VipUserDao;
import com.ship.domain.UserBank;
import com.ship.domain.VipUser;
import com.ship.handler.UserBankHandler;
import com.ship.pojo.request.BankInfo;

import java.io.Serializable;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/14.
 * UserBankHandler 实现
 */
@Transactional
public class UserBankHandlerImpl implements UserBankHandler {

    @Inject
    private UserBankDao userBankDao;

    @Inject
    private VipUserDao vipUserDao;

    @Override
    public UserBank find(long id) {
        return userBankDao.find(id);
    }

    @Override
    public void update(UserBank entity) {
        userBankDao.update(entity);
    }

    @Override
    public void delete(Serializable... entityIds) {
        userBankDao.delete(entityIds);
    }

    @Override
    public void save(UserBank entity) {
        userBankDao.save(entity);
    }


    @Override
    public UserBank getUserAccountInfo(Long userId) {
        return userBankDao.getUserAccountInfo(userId);
    }

    @Override
    public String saveBank(BankInfo ub,Long userId) {
        UserBank user = new UserBank();

        VipUser vs = vipUserDao.find(userId);
        if (!vs.getRealname().equals(ub.getBankaccountname())){
            return "银行账户名称错误";
        }

        if (StringUtil.isEmpty(ub.getAlipayAccount())){
            user.setBankaccountname(ub.getBankaccountname());
        }else{
            user.setBankaccountname("");
        }
        user.setAlipayAccount(ub.getAlipayAccount());
        user.setAddress(ub.getAddress());
        user.setType(ub.getBankType());
        user.setBankId(ub.getBankId());
        user.setBanknum(ub.getBanknum());
        user.setUserId(userId);
        user.setAddTime(DateUtil.getDate());
        userBankDao.save(user);
        return  "";
    }
}

