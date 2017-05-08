package com.ship.handler.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.ship.common.util.*;
import com.ship.dao.VipUserDao;
import com.ship.pojo.UserVo;
import com.ship.pojo.error.SuaryMess;
import com.ship.pojo.error.SysError;
import com.ship.handler.VipUserHandler;
import com.ship.domain.VipUser;
import com.ship.pojo.request.VipUserReq;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * VipUserHandler 实现
 */
@Transactional
public class VipUserHandlerImpl implements VipUserHandler{

    @Inject
    private VipUserDao vipUserDao;

    public void save(VipUser entity) {
        vipUserDao.save(entity);
    }

    public void delete(Serializable... entityIds) {
        vipUserDao.delete(entityIds);
    }

    public void update(VipUser entity) {
        vipUserDao.update(entity);
    }

    public VipUser find(long id) {

        return vipUserDao.find(id);
    }

    public VipUser userlogin(String account, String password) {
        return vipUserDao.userlogin(account,password);
    }

    public VipUser getUserPhone(String phone) {
        return vipUserDao.getUserPhone(phone);
    }

    @Override
    public List<String> lenovoAccount(String account) {
        return vipUserDao.lenovoAccount(account);
    }

    @Override
    public VipUser getVipName(String vipname) {
        return vipUserDao.getVipName(vipname);
    }

    @Override
    public VipUser getRefer(String phone) {
        return vipUserDao.getRefer(phone);
    }

    public Page<UserVo> getVipUser(FindParam param, Page<UserVo> page) {
        return vipUserDao.getVipUser(param,page);
    }

    public String vipRegister(VipUserReq vu,int mark) {
        boolean flay = false;
        // TODO: 获取推荐人信息
        VipUser vipUser  = new VipUser();
        VipUser referUser =  vipUserDao.getUserPhone(vu.getReferrercode());
        if(referUser != null){
            // TODO: 简接推荐
            VipUser re =  vipUserDao.getUserPhone(referUser.getReferrercode());
            if (re == null) {
                referUser.setIndirectId(StateCode.STRONE);
            }else{
                referUser.setIndirectId(re.getId().toString());
            }
            vipUserDao.update(referUser);
        }else{
            vipUser.setIndirectId(StateCode.STRNULL);
        }
        // TODO: 保存用户信息
        vipUser.setIdcard(vu.getIdcard());
        vipUser.setAddTime(DateUtil.getDate());
        vipUser.setReferrercode(vu.getReferrercode());
        vipUser.setPhone(vu.getPhone());
        vipUser.setPassword(Encryption.MD5(vu.getPassword()));
        if (mark == 0){
            vipUser.setIslock(StateCode.NUMTWO);
        }else{
            vipUser.setIslock(mark);
        }
        vipUser.setGradeone(StateCode.DOUONE);
        vipUser.setGradetwo(StateCode.DOUONE);
        vipUser.setGradethree(StateCode.DOUONE);
        vipUser.setEmail(StateCode.STRNULL);
        vipUser.setAlready(StateCode.DOUONE);
        vipUser.setLoginnum(StateCode.NUMTWO);
        vipUser.setTotal(StateCode.DOUONE);
        vipUser.setPernum(StateCode.NUMTWO);
        vipUser.setHeadUrl(StateCode.STRNULL);
        vipUser.setLoginnum(StateCode.NUMTWO);
        vipUser.setVipname(vu.getVipname());
        vipUser.setRealname(vu.getRealname());
        vipUser.setBalance(StateCode.DOUONE);
        vipUserDao.save(vipUser);
        UserInfo.setId(vipUser.getId());
        Map<String,String> map  = new HashMap<String,String>();
        map.put("userId",vipUser.getId().toString());
        return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode(),map);
    }


}
