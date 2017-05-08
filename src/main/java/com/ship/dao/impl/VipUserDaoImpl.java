package com.ship.dao.impl;

import com.google.inject.Inject;
import com.ship.common.jpa.BaseDaoImpl;
import com.ship.common.util.FindParam;
import com.ship.common.util.Page;
import com.ship.common.util.StringUtil;
import com.ship.dao.VipUserDao;
import com.ship.pojo.UserVo;
import com.ship.domain.VipUser;

import java.util.*;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * VipUserDao 实现
 */
public class VipUserDaoImpl extends BaseDaoImpl<VipUser> implements VipUserDao {



    @Override
    public VipUser userlogin(String account, String password) {
        String sql = "phone = ? and password = ?";
        List<Object> rlist = new ArrayList<Object>();
        rlist.add(account);
        rlist.add(password);
        return super.getEntityData(sql,rlist.toArray());
    }

    @Override
    public List<String> lenovoAccount(String account) {
        String  entity = "select  phone  from  vipuser where phone like '%"+account+"%'";
        List<String> rlist = em.createNativeQuery(entity).getResultList();
        List<String> reList = new ArrayList<String>();
        for(int i = 0;i<rlist.size();i++){
            reList.add(rlist.get(i));
        }
       return reList;
    }

    @Override
    public VipUser getVipName(String vipname) {
        String sql = "vipname = ?";
        List<Object> rlist = new ArrayList<Object>();
        rlist.add(vipname);
        return super.getEntityData(sql,rlist.toArray());
    }

    @Override
    public VipUser getRefer(String phone) {
        String sql = "referrercode = ?";
        List<Object> rlist = new ArrayList<Object>();
        rlist.add(phone);
        return super.getEntityData(sql,rlist.toArray());
    }

    @Override
    public VipUser getUserPhone(String phone) {
        String sql = "phone = ?";
        List<Object> rlist = new ArrayList<Object>();
        rlist.add(phone);
        return super.getEntityData(sql,rlist.toArray());
    }


    @Override
    public Page<UserVo> getVipUser(FindParam param, Page<UserVo> page) {
        List<Object> params = new ArrayList<Object>();
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        StringBuffer jpql = new StringBuffer();
        StringBuffer sql = new StringBuffer();
        StringBuffer entity = new StringBuffer();
        sql.append("o.id,o.vipname,o.loginTime,o.realname,o.loginIp,");
        sql.append("(case when o.loginnum is not null  then o.loginnum else '-' end ) as loginnum ,");
        sql.append(" o.islock,o.headUrl,o.total,o.pernum,o.phone,o.referrercode,");
        sql.append("(case when di.vipname is not null  then di.vipname else '-' end ) as diname ");
        entity.append("vipuser o    ");
        entity.append("LEFT JOIN (SELECT u.id,u.indirectId,u.vipname  ");
        entity.append("FROM vipuser u ) di on  ");
        entity.append(" di.indirectId =  o.id  ");
        orderBy.put("addTime", "desc");
        if (StringUtil.isNotEmpty(param.getUsername())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("o.vipname = ?");
            params.add(param.getUsername());
        }
        if (StringUtil.isNotEmpty(param.getState())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("o.islock = ?");
            params.add(param.getState());
        }
        if (StringUtil.isNotEmpty(param.getStartDateTime()) && StringUtil.isNotEmpty(param.getEndDateTime())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("o.addTime  >= ? and o.addTime < ?");
            params.add(param.getStartDateTime());
            params.add(param.getEndDateTime());
        }
        Page<Object> pageObj = new Page<Object>();
        pageObj.setQueryResult(super.getScrollNativeData(page.getCurrentpage(),page.getMaxresult(),jpql.toString(), params.toArray(), orderBy, sql.toString(), entity.toString()));
        Iterator<Object> iterator =  pageObj.getRecords().iterator();
        UserVo vu = null;
        List<UserVo> godList = new ArrayList<UserVo>();
        while (iterator.hasNext()){
            Object[] obj = (Object[])iterator.next();
            vu  = new UserVo();
            vu.setUserId(Long.parseLong(obj[0].toString()));
            vu.setVipname(obj[1].toString());
            vu.setLoginDate(obj[2]==null?"":obj[2].toString());
            vu.setRealname(obj[3].toString());
            vu.setLoginIp(obj[4]==null?"":obj[4].toString());
            vu.setLoginnum(Integer.parseInt(obj[5].toString()));
            vu.setIslock(Integer.parseInt(obj[6].toString()));
            vu.setHeadUrl(obj[7]==null?"":obj[7].toString());
            vu.setTotal(obj[8]==null?0:Double.parseDouble(obj[8].toString()));
            vu.setPernum(obj[9]==null?0:Integer.parseInt(obj[9].toString()));
            vu.setPhone(obj[10]==null?"":obj[10].toString());
            vu.setReferrercode(obj[11]==null?"":obj[11].toString());
            if (StringUtil.isNotEmpty(vu.getReferrercode()) && !(vu.getReferrercode().equals("0"))){
                VipUser s =  getUserPhone(vu.getReferrercode());
                vu.setRefername(s.getVipname() == null ? "" : s.getVipname());
            }else{
                vu.setRefername("");
            }
            vu.setDiname(obj[12]==null?"":obj[12].toString());
            godList.add(vu);
        }
        page.setRecords(godList);
        page.setTotalrecord(pageObj.getTotalrecord());
        return page;
    }
}