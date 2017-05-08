package com.ship.common.jpa;

import com.ship.common.util.QueryResult;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Mryang
 * @createTime 2017年3月28日
 * @version 1.0
 * 数据访问dao
 */
public interface BaseDao<T>{
	/**
	 * 保存实体对象
	 * @param entity
	 */
	public void save(T entity);
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public T find(long id);
	
	/**
	 * 主键删除
	 * @param entityIds
	 */
	public void delete(Serializable... entityIds);
	/**
	 * 修改实体对象
	 * @param entity
	 */
	public void update(T entity);
	/**
	 * 获取实体，根据查询条件
	 * @param whereJpql
	 * @param queryParams
	 * @return
	 */
	public T getEntityData(String whereJpql, Object[] queryParams);

	/**
	 * @param whereJpql
	 * @param queryParams
	 * @param orderBy
	 * @return
	 */
	public List<T> getEntityData(String whereJpql, Object[] queryParams, LinkedHashMap<String, String> orderBy);

	/**
	 * 获取分页数据，Native SQL
	 * @param firstindex
	 * @param maxresult
	 * @param whereJpql
	 * @param queryParams
	 * @param orderBy
	 * @param sql
	 * @param entity
	 * @return
	 */
	public QueryResult<Object> getScrollNativeData(int firstindex, int maxresult, String whereJpql, Object[] queryParams, LinkedHashMap<String, String> orderBy, String sql, String entity);


	/**
	 * 获取分页数据，Native SQL
	 * <br>
	 * 分组+排序
	 * @param firstindex
	 * @param maxresult
	 * @param whereJpql
	 * @param queryParams
	 * @param groupBy
	 * @param orderBy
	 * @param sql
	 * @param entity
	 * @return
	 */
	public QueryResult<Object> getScrollNativeDataGroupBy(int firstindex, int maxresult, String whereJpql, Object[] queryParams, Object[] groupBy, LinkedHashMap<String, String> orderBy, String sql, String entity);


}
