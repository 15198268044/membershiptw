package com.ship.dao;
import com.ship.common.jpa.BaseDao;
import com.ship.domain.SmsCode;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 *  验证码dao
 */
public interface SmsCodeDao extends BaseDao<SmsCode> {

    /**
     * 判断是否有改账户的验证码
     * @param phone
     * @return
     */
    public SmsCode isPhoneSms(String phone);
	
	
}
