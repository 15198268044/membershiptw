package com.ship.handler.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.ship.common.util.*;
import com.ship.dao.SysParamDao;
import com.ship.dao.UserBankDao;
import com.ship.dao.VipUserDao;
import com.ship.dao.WithDrawalsDao;
import com.ship.handler.UserBankHandler;
import com.ship.pojo.DrawalsStateVo;
import com.ship.pojo.WithDrawalVo;
import com.ship.pojo.error.DrawalsMess;
import com.ship.pojo.error.SysError;
import com.ship.pojo.request.DrawlsRequest;
import com.ship.handler.WithDrawalsHandler;
import com.ship.domain.SysParam;
import com.ship.domain.UserBank;
import com.ship.domain.VipUser;
import com.ship.domain.WithDrawals;
import com.ship.domain.enums.ParamKey;
import java.io.Serializable;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * WithDrawalsHandler 实现
 */
@Transactional
public class WithDrawalsHandlerImpl implements WithDrawalsHandler {

    @Inject
    private WithDrawalsDao withDrawalsDao;

    @Inject
    private SysParamDao sysParamDao;

    @Override
    public void save(WithDrawals entity) {
        withDrawalsDao.save(entity);
    }

    @Override
    public void delete(Serializable... entityIds) {
        withDrawalsDao.delete(entityIds);
    }

    @Override
    public void update(WithDrawals entity) {
        withDrawalsDao.update(entity);
    }

    @Override
    public WithDrawals find(long id) {
        return withDrawalsDao.find(id);
    }


    @Override
    public Page<WithDrawalVo> getUserDrawals(FindParam drawals, Page<WithDrawalVo> page) {
        return withDrawalsDao.getUserDrawals(drawals,page);
    }

    @Override
    public Double getDrawalMoney() {
        return withDrawalsDao.getDrawalMoney();
    }

    @Override
    public DrawalsStateVo getDrawalsStateMoney() {
        return withDrawalsDao.getDrawalsStateMoney();
    }

    @Override
    public String withDrawalsApply(DrawlsRequest da) {
        try{
            // TODO:insert体现记录
            WithDrawals drawals = new WithDrawals();
            SysParam param =  sysParamDao.getSysParam(ParamKey.USERGRADE.getContent());
            int fee =  Integer.parseInt(param.getJson());
            // TODO: 计算手续费
            Double money =  NumberUtil.calc(da.getTatalmoney(),fee);
            drawals.setUserId(da.getUserId());
            drawals.setBankId(da.getBankId());
            drawals.setSerialnumber(StringUtil.getSerial());
            drawals.setStatus(StateCode.NUMTWO);
            drawals.setTypes(da.getType());
            drawals.setMark(" ");
            drawals.setCounterFee(money);
            drawals.setTatalmoney(da.getTatalmoney());
            drawals.setMoney(da.getTatalmoney() - money);
            drawals.setMark(StateCode.STRNULL);
            drawals.setApplyTime(DateUtil.getTimestamp());
            withDrawalsDao.save(drawals);
            return BaseResponse.of(Status.SUCCESS, SysError.SUCCESS.getErrMessage(),SysError.SUCCESS.getErrCode());
        }catch (Exception e){
            return BaseResponse.of(Status.ERROR, SysError.SYS_ERROR.getErrMessage());
        }
    }





}
