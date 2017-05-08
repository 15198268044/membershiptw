package com.ship.common.jpa;

import java.io.Serializable;

/**
 * @author Mryang
 * @createTime 2017年3月28日
 * @version 1.0
 * BaseServer 
 */
public interface BaseServer<T> {
	
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

}
