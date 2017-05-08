package com.ship.handler.impl;
import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.ship.common.util.*;
import com.ship.dao.BranchRecordDao;
import com.ship.dao.SysParamDao;
import com.ship.dao.VipUserDao;
import com.ship.handler.BranchRecordHandler;
import com.ship.domain.BranchRecord;
import com.ship.domain.SysParam;
import com.ship.domain.VipUser;
import com.ship.domain.enums.ParamGrade;
import com.ship.domain.enums.ParamKey;
import java.io.Serializable;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * BranchRecordHandler 实现
 */
@Transactional
public class BranchRecordHandlerImpl implements BranchRecordHandler{

    @Inject
    private BranchRecordDao branchRecordDao;


    @Override
    public void save(BranchRecord entity) {
        branchRecordDao.save(entity);
    }

    @Override
    public void delete(Serializable... entityIds) {
        branchRecordDao.delete(entityIds);
    }

    @Override
    public void update(BranchRecord entity) {
        branchRecordDao.update(entity);
    }

    @Override
    public BranchRecord find(long id) {
        return branchRecordDao.find(id);
    }


    @Override
    public Page<BranchRecord> getBranchList(FindParam param, Page<BranchRecord> page) {
        return branchRecordDao.getBranchList(param,page);
    }





}
