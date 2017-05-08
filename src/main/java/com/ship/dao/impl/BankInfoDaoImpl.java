package com.ship.dao.impl;
import com.ship.common.jpa.BaseDaoImpl;
import com.ship.dao.BankInfoDao;
import com.ship.domain.BankInfo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * BankInfoDao 实现
 */
public class BankInfoDaoImpl extends BaseDaoImpl<BankInfo> implements BankInfoDao {




    public List<BankInfo> getBankList(Integer mark) {
        String sql = "";
        List<Object> arr = new ArrayList<Object>();
        if (mark  == 1){
            sql = "status = 1";
        }
        LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
        map.put("addTime","desc");
        return super.getEntityData(sql,arr.toArray(),map);
    }


}
