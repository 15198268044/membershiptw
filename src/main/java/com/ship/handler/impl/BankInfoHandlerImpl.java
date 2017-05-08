package com.ship.handler.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.ship.dao.BankInfoDao;
import com.ship.handler.BankInfoHandler;
import com.ship.domain.BankInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * BankInfoHandler 实现
 */
@Transactional
public class BankInfoHandlerImpl implements BankInfoHandler {

    @Inject
    private BankInfoDao bankInfoDao;

    @Override
    public BankInfo find(long id) {
        return bankInfoDao.find(id);
    }

    @Override
    public void update(BankInfo entity) {
        bankInfoDao.update(entity);
    }

    @Override
    public void delete(Serializable... entityIds) {
        bankInfoDao.delete(entityIds);
    }

    @Override
    public void save(BankInfo entity) {
        bankInfoDao.save(entity);
    }


    @Override
    public List<BankInfo> getBankList(Integer mark) {
        return bankInfoDao.getBankList(mark);
    }


}
