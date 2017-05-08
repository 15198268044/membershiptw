package com.ship.handler;
import com.ship.common.jpa.BaseServer;
import com.ship.common.util.StringUtil;
import com.ship.domain.SmsCode;

/**
 * @author Mryang
 * @createTime 2017年3月20日
 * @version 1.0
 * 验证码处理
 */
public interface SmsCodeHander extends BaseServer<SmsCode> {

    /**
     * 判断是否有改账户的验证码
     * @param phone
     * @return
     */
    public SmsCode isPhoneSms(String phone);


}
