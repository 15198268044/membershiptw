package com.ship.dao.impl;
import com.ship.common.jpa.BaseDaoImpl;
import com.ship.common.util.FindParam;
import com.ship.common.util.Page;
import com.ship.common.util.StringUtil;
import com.ship.dao.BranchRecordDao;
import com.ship.domain.BranchRecord;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * BranchRecordDao 实现
 */
public class BranchRecordDaoImpl extends BaseDaoImpl<BranchRecord> implements BranchRecordDao{


    public Page<BranchRecord> getBranchList(FindParam param, Page<BranchRecord> page) {
        List<Object> params = new ArrayList<Object>();
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        StringBuffer jpql = new StringBuffer();
        String sql = "o.id,o.tatalmoney,o.realname,o.serialnumber,o.mark,o.vipId,o.grade,o.actualmoney,o.addTime ";
        String entity = " branchrecord o ";
        orderBy.put("addTime", "desc");
        //用户Id
        if (StringUtil.isNotEmpty(param.getUserId())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("o.vipId = ?");
            params.add(param.getUserId());
        }

        if (StringUtil.isNotEmpty(param.getState())) {
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("o.grade = ?");
            params.add(param.getState());
        }

        if (StringUtil.isNotEmpty(param.getSerialnumber())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("o.serialnumber = ?");
            params.add(param.getSerialnumber());
        }

        if (StringUtil.isNotEmpty(param.getStartDateTime())){
            if (params.size() > 0) {
                jpql.append(" and ");
            }
            jpql.append("o.addTime > ?");
            params.add(param.getStartDateTime());
        }

       if (StringUtil.isNotEmpty(param.getStartDateTime()) && StringUtil.isNotEmpty(param.getEndDateTime())){
           jpql.append(" and ");
           jpql.append("o.addTime  >= ? and o.addTime < ?");
           params.add(param.getStartDateTime());
           params.add(param.getEndDateTime());
        }

        Page<Object> pageObj = new Page<Object>();
        pageObj.setQueryResult(super.getScrollNativeData(page.getCurrentpage(),page.getMaxresult(),jpql.toString(), params.toArray(), orderBy, sql, entity));
        Iterator<Object> iterator =  pageObj.getRecords().iterator();
        BranchRecord drawalVo = null;
        List<BranchRecord> godList = new ArrayList<BranchRecord>();
        while (iterator.hasNext()){
            Object[] obj = (Object[])iterator.next();
            drawalVo = new BranchRecord();
            drawalVo.setId(Long.parseLong(obj[0].toString()));
            drawalVo.setTatalmoney(Double.parseDouble(obj[1].toString()));
            drawalVo.setSerialnumber(obj[3].toString());
            drawalVo.setMark(obj[4].toString());
            drawalVo.setVipId(Long.parseLong(obj[5].toString()));
            drawalVo.setGrade(Integer.parseInt(obj[6].toString()));
            if (drawalVo.getGrade() == 1){
                String s =  obj[2].toString();
                String replace = s.replace(s,"*"+s.substring(1, s.length()));
                drawalVo.setRealname(replace);
                drawalVo.setUsergrade("一级用户");
            }else if (drawalVo.getGrade() == 2){
                drawalVo.setRealname("***");
                drawalVo.setUsergrade("二级用户");
            }else{
                drawalVo.setRealname("***");
                drawalVo.setUsergrade("三级用户");
            }
            drawalVo.setActualmoney(Double.parseDouble(obj[7].toString()));
            drawalVo.setDateTime(obj[8].toString());
            godList.add(drawalVo);
        }
        page.setRecords(godList);
        page.setTotalrecord(pageObj.getTotalrecord());
        return page;
    }













}
