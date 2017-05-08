package com.ship.dao.impl;
import com.ship.common.jpa.BaseDaoImpl;
import com.ship.dao.SmsCodeDao;
import com.ship.domain.SmsCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * SmsCodeDao 实现
 */
public class SmsCodeDaoImpl extends BaseDaoImpl<SmsCode> implements SmsCodeDao {


    @Override
    public SmsCode isPhoneSms(String phone) {
        List<Object> param = new ArrayList<Object>();
        param.add(phone);
        String sql = "phone = ?";
        return  super.getEntityData(sql,param.toArray());
    }
}
