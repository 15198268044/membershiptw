package com.ship.dao.impl;

import com.ship.common.jpa.BaseDaoImpl;
import com.ship.common.util.DateUtil;
import com.ship.common.util.FindParam;
import com.ship.common.util.Page;
import com.ship.common.util.StringUtil;
import com.ship.dao.WithDrawalsDao;
import com.ship.pojo.DrawalsStateVo;
import com.ship.pojo.WithDrawalVo;
import com.ship.domain.WithDrawals;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * WithDrawalsDao 实现
 */
public class WithDrawalsDaoImpl extends BaseDaoImpl<WithDrawals> implements WithDrawalsDao {


    @Override
    public Page<WithDrawalVo> getUserDrawals(FindParam drawals, Page<WithDrawalVo> page) {
        List<Object> params = new ArrayList<Object>();
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        StringBuffer jpql = new StringBuffer();
        StringBuffer sql = new StringBuffer();
        sql.append("o.id,  (case when o.applyTime is not null  then o.applyTime else '-' end ) as applyTime");
        sql.append(",o.counterFee,o.money,o.tatalmoney,(case when o.handlerTime is not null ");
        sql.append(" then o.handlerTime else '-' end ) as handlerTime,   ");
        sql.append(" o.`status`,o.mark,o.serialnumber, ");
        sql.append(" o.types,u.address,u.bankaccountname,u.banknum,");
        sql.append("(case when b.bankname is not null  then b.bankname else '-' end ) as bankname ,");
        sql.append(" v.realname , v.phone ");
        String entity = "withdrawals o LEFT JOIN userbank u ON o.bankId = u.id LEFT JOIN bankinfo b ON u.bankId = b.id inner JOIN vipuser v on v.id = o.userId ";
        orderBy.put("applyTime", "desc");
        //用户Id
        if (StringUtil.isNotEmpty(drawals.getUserId())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("o.userId = ?");
            params.add(drawals.getUserId());
        }
        //银行账户
        if (StringUtil.isNotEmpty(drawals.getBankAccount())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("u.banknum like  '%"+drawals.getBankAccount()+"%' ");

        }
        //电话
        if (StringUtil.isNotEmpty(drawals.getTelphone())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("v.phone = ?");
            params.add(drawals.getTelphone());
        }

        //申请人
        if (StringUtil.isNotEmpty(drawals.getUsername())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("v.realname = ?");
            params.add(drawals.getUsername());
        }


        if (StringUtil.isNotEmpty(drawals.getBankId())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("u.bankId = ?");
            params.add(drawals.getBankId());
        }

        if (StringUtil.isNotEmpty(drawals.getSerialnumber())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("o.serialnumber = ?");
            params.add(drawals.getSerialnumber());
        }


        if (StringUtil.isNotEmpty(drawals.getState())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("o.status = ?");
            params.add(drawals.getState());
        }

        if (StringUtil.isNotEmpty(drawals.getStartDateTime())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }

            System.out.println(DateUtil.formatDateHHmmss(drawals.getStartDateTime()));
            jpql.append("o.applyTime >= ?");
            params.add(drawals.getStartDateTime());
        }

        if (StringUtil.isNotEmpty(drawals.getStartDateTime()) && StringUtil.isNotEmpty(drawals.getEndDateTime())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("o.applyTime  >= ? and o.applyTime < ?");
            params.add(drawals.getStartDateTime());
            params.add(drawals.getEndDateTime());
        }

        Page<Object> pageObj = new Page<Object>();
        pageObj.setQueryResult(super.getScrollNativeData(page.getCurrentpage(),page.getMaxresult(),jpql.toString(), params.toArray(), orderBy, sql.toString(), entity));
        Iterator<Object> iterator =  pageObj.getRecords().iterator();
        WithDrawalVo drawalVo = null;
        List<WithDrawalVo> godList = new ArrayList<WithDrawalVo>();
        while (iterator.hasNext()){
            /*

	(
		CASE
		WHEN b.bankname IS NOT NULL THEN
			b.bankname
		ELSE
			'-'
		END
	) AS bankname,
	v.realname,
	v.phone
             */
            Object[] obj = (Object[])iterator.next();
            drawalVo = new WithDrawalVo();
            drawalVo.setId(Long.parseLong(obj[0].toString()));
            drawalVo.setApplyTime(obj[1].toString());
            drawalVo.setCounterFee(Double.parseDouble(obj[2].toString()));
            drawalVo.setMoney(Double.parseDouble(obj[3].toString()));
            drawalVo.setTatalmoney(Double.parseDouble(obj[4].toString()));
            drawalVo.setHandlerTime(obj[5].toString()==null?"":obj[5].toString());
            switch (Integer.parseInt(obj[6].toString())){
                case   0 :
                    drawalVo.setStatus("申请中");
                break;
                case  1:
                    drawalVo.setStatus("银行处理中");
                    break;
                case   2 :
                    drawalVo.setStatus("提现成功");
                    break;
                    default:
                  drawalVo.setStatus("提现失败");
            }
            drawalVo.setMark(obj[7].toString());
            drawalVo.setSerialnumber(obj[8].toString());
            drawalVo.setType(Integer.parseInt(obj[9].toString()));
            WithDrawalVo.BankInfoRequest bfq = new WithDrawalVo.BankInfoRequest();
            bfq.setAddress(obj[10].toString()==null?"":obj[10].toString());
            bfq.setBankaccountname(obj[11].toString()==null?"":obj[11].toString());
            bfq.setBanknum(obj[12].toString()==null?"":obj[12].toString());
            bfq.setBankname(obj[13].toString()==null?"":obj[13].toString());
            drawalVo.setBankInfo(bfq);
            drawalVo.setRealname(obj[14].toString());
            drawalVo.setPhone(obj[15].toString());
            godList.add(drawalVo);
        }
        page.setRecords(godList);
        page.setTotalrecord(pageObj.getTotalrecord());
        return page;
    }


    @Override
    public DrawalsStateVo getDrawalsStateMoney() {
        DrawalsStateVo vo = new DrawalsStateVo();
        StringBuilder builder = new StringBuilder();
        builder.append("select sum(o.money) AS money , o.`status` from withdrawals ");
        builder.append("o group by o. status having o.`status` = 0 ");
        builder.append(" or o.`status` = 2 ");
        List rlist =  em.createNativeQuery(builder.toString()).getResultList();
        if (rlist == null || rlist.size() == 0){
            vo.setOne(0.0);
            vo.setTwo(0.0);
        }else{
            for (int i = 0;i<rlist.size();i++) {
                Object[] obj =  ( Object[] )rlist.get(i);
                if (obj[1].toString().equals("0")){
                    vo.setOne(Double.parseDouble(obj[0].toString()));
                }
                if (obj[1].toString().equals("2")){
                    vo.setTwo(Double.parseDouble(obj[0].toString()));
                }

            }
        }

        List  ts  =    em.createQuery(" SELECT SUM(o.balance) as money  from  VipUser o").getResultList();
        vo.setThree(Double.parseDouble(ts.get(0).toString()));
        return vo;
    }

    @Override
    public Double getDrawalMoney() {
        String  entity = "select  SUM(money) as money  from  WithDrawals where status = 2";
        Double d =  (Double)em.createQuery(entity).getSingleResult();
        if(StringUtil.isEmpty(d)){
            return  0.0;
        }else{
            return d ;
        }
    }


}
