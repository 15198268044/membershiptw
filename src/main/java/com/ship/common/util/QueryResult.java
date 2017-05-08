package com.ship.common.util;

import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @createTime 2017/4/7.
 * 查询结果
 */
public class QueryResult<T> {

    private List<T> resultlist;
    private long totalrecord;
    public List<T> getResultlist() {
        return resultlist;
    }

    public void setResultlist(List<T> resultlist) {
        this.resultlist = resultlist;
    }

    public long getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(long totalrecord) {
        this.totalrecord = totalrecord;
    }
}
