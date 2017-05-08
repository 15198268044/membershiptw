package com.ship.handler;

import com.ship.common.jpa.BaseServer;
import com.ship.common.util.FindParam;
import com.ship.common.util.Page;
import com.ship.pojo.DrawalsStateVo;
import com.ship.pojo.WithDrawalVo;
import com.ship.pojo.request.DrawlsRequest;
import com.ship.domain.WithDrawals;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * 提现处理
 */
public interface WithDrawalsHandler extends BaseServer<WithDrawals> {

    /**
     * 提现申请
     * @param drawalsApply
     * @return
     */
    public String  withDrawalsApply(DrawlsRequest drawalsApply);

    /**
     * 获取用户申请提现记录
     * @param drawals
     * @param page
     * @return
     */
    public Page<WithDrawalVo> getUserDrawals(FindParam drawals, Page<WithDrawalVo> page);

    /**
     * 获取提现状态金额
     * @return
     */
    public DrawalsStateVo getDrawalsStateMoney();

    /**
     * 获取用户已提现金额
     * @return
     */
    public Double getDrawalMoney();


}
