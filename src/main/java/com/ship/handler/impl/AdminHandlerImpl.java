package com.ship.handler.impl;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.ship.dao.AdminDao;
import com.ship.handler.AdminHandler;
import com.ship.domain.DbAdmin;
import java.io.Serializable;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/1.
 * AdminHandler  实现
 */
@Transactional
public class AdminHandlerImpl implements AdminHandler {

    @Inject
    private AdminDao adminDao;


    public DbAdmin find(long id) {
        return adminDao.find(id);
    }

    public void delete(Serializable... entityIds) {
        adminDao.delete(entityIds);
    }

    public void update(DbAdmin entity) {
        adminDao.update(entity);
    }

    public void save(DbAdmin entity) {
        adminDao.save(entity);
    }

    public DbAdmin login(String account, String password) {
        return adminDao.login(account,password);
    }
}
