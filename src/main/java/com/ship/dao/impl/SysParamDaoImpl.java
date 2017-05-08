package com.ship.dao.impl;

import com.ship.common.jpa.BaseDaoImpl;
import com.ship.dao.SysParamDao;
import com.ship.domain.SysParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/5.
 * SysParamDao 实现
 */
public class SysParamDaoImpl extends BaseDaoImpl<SysParam> implements SysParamDao{

    public SysParam getSysParam(String parKey) {
        String sql = "parkey = ?";
        List<Object> list = new ArrayList<Object>();
        list.add(parKey);
        return   super.getEntityData(sql,list.toArray());
    }


    public List<SysParam> getParamList() {
        List<Object> arr = new ArrayList<Object>();
        LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
        return super.getEntityData("",arr.toArray(),map);
    }
}
