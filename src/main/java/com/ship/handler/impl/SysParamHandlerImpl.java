package com.ship.handler.impl;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.ship.dao.SysParamDao;
import com.ship.handler.SysParamHandler;
import com.ship.domain.SysParam;
import java.io.Serializable;
import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 */
@Transactional
public class SysParamHandlerImpl implements SysParamHandler{

    @Inject
    private SysParamDao sysParamDao;

    public void save(SysParam entity) {
        sysParamDao.save(entity);
    }

    public void delete(Serializable... entityIds) {
        sysParamDao.delete(entityIds);
    }

    public void update(SysParam entity) {
        sysParamDao.update(entity);
    }

    public SysParam find(long id) {
        return sysParamDao.find(id);
    }


    public SysParam getSysParam(String parKey) {
        return sysParamDao.getSysParam(parKey);
    }

    public List<SysParam> getParamList() {
        return sysParamDao.getParamList();
    }
}
