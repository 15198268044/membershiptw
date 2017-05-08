package com.ship.common.jpa;

import com.google.inject.Inject;
import com.ship.common.util.GenericsUtils;
import com.ship.common.util.QueryResult;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;


@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
	
	@Inject
	@PersistenceContext
	protected EntityManager em;

	public void save(T entity) {
		em.persist(entity);
	}

	public T find(long id) {
		clear();
		return em.find(entityClass, id);
	}

	public void delete(Serializable... entityIds) {
		 for (Object id : entityIds) {
			 em.remove(em.getReference(this.entityClass, id));
	     }
	}

	public void update(T entity) {
		em.merge(entity);
	}


	public void flush() {
		em.flush();
	}

	public void clear() {
		em.clear();
	}



	public T getEntityData(String whereJpql, Object[] queryParams) {
		String entityName = getEntityName(this.entityClass);
		clear();
		Query query = em.createQuery("select o from " + entityName + " o " + (whereJpql == null || "".equals(whereJpql.trim()) ? "" : " where " + whereJpql));
		setQueryParams(query, queryParams);
		List<T> resultList = query.getResultList();
		if (resultList.size() > 0) {
			return resultList.get(0);
		}
		return null;
	}

	/**
	 * 获取实体数据集合
	 * @param whereJpql
	 * @param queryParams
	 * @param orderBy
	 * @return
	 */
	public List<T> getEntityData(String whereJpql, Object[] queryParams, LinkedHashMap<String, String> orderBy) {
		String entityName = getEntityName(this.entityClass);
		clear();
		Query query = em.createQuery("select o from " + entityName + " o " + (whereJpql == null || "".equals(whereJpql.trim()) ? "" : " where " + whereJpql) + buildOrderBy(orderBy));
		setQueryParams(query, queryParams);
		List<T> resultList = query.getResultList();
		if (resultList.size() > 0) {
			return resultList;
		}
		return null;
	}



	public QueryResult<Object> getScrollNativeData(int firstindex, int maxresult, String whereJpql, Object[] queryParams, LinkedHashMap<String, String> orderBy, String sql, String entity) {
		QueryResult<Object> qr = new QueryResult<Object>();
		clear();
		Query query = em.createNativeQuery("select " + sql + " from " + entity + (whereJpql == null || "".equals(whereJpql.trim()) ? "" : " where " + whereJpql) + buildOrderBy(orderBy));
		setQueryParams(query, queryParams);
		if (firstindex != -1 && maxresult != -1) {
			query.setFirstResult((firstindex -1) * maxresult).setMaxResults(maxresult);
		}
		qr.setResultlist(query.getResultList());
		query = em.createNativeQuery("select count(*) from " + entity + (whereJpql == null || "".equals(whereJpql.trim()) ? "" : " where " + whereJpql));
		setQueryParams(query, queryParams);
		qr.setTotalrecord(Long.parseLong(query.getSingleResult().toString()));
		return qr;
	}



	public QueryResult<Object> getScrollNativeDataGroupBy(int firstindex, int maxresult, String whereJpql, Object[] queryParams, Object[] groupBy, LinkedHashMap<String, String> orderBy, String sql, String entity) {
		QueryResult<Object> qr = new QueryResult<Object>();
		clear();
		Query query = em.createNativeQuery("select " + sql + " from " + entity + (whereJpql == null || "".equals(whereJpql.trim()) ? "" : " where " + whereJpql) + buildGroupBy(groupBy) + buildOrderBy(orderBy));
		setQueryParams(query, queryParams);
		if (firstindex != -1 && maxresult != -1) {
			query.setFirstResult((firstindex -1) * maxresult).setMaxResults(maxresult);
		}
		qr.setResultlist(query.getResultList());
		query = em.createNativeQuery("select count(o.c) from (select count(*) as c from " + entity + (whereJpql == null || "".equals(whereJpql.trim()) ? "" : " where " + whereJpql) + buildGroupBy(groupBy)+") o");
		setQueryParams(query, queryParams);
		qr.setTotalrecord(Long.parseLong(query.getSingleResult().toString()));
		return qr;
	}


	/**
	 * 组装 group by 语句
	 * @param groupBy
	 * @return
	 */
	protected static String buildGroupBy(Object[] groupBy) {
		StringBuffer orderbyql = new StringBuffer();
		if(groupBy != null && groupBy.length > 0) {
			orderbyql.append(" group by");
			for(int i = 0; i < groupBy.length; i++) {
				orderbyql.append(" o.").append(groupBy[i]).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length() - 1);
		}
		return orderbyql.toString();
	}


	/**
	 * 获取实体名称
	 * @param <E>
	 * @param clazz 实体类
	 * @return
	 */
	protected static <E> String getEntityName(Class<E> clazz) {
		String entityName = clazz.getSimpleName();
		Entity entity = clazz.getAnnotation(Entity.class);
		if(entity.name() != null && !"".equals(entity.name())) {
			entityName = entity.name();
		}
		return entityName;
	}


	/**
	 * 设置参数
	 * @param query
	 * @param queryParams
	 */
	protected static void setQueryParams(Query query, Object[] queryParams) {
		if(queryParams != null && queryParams.length > 0) {
			for(int i = 0; i < queryParams.length; i++) {
				query.setParameter(i+1, queryParams[i]);
			}
		}
	}


	/**
	 * 组装 order by 语句
	 * @param  orderBy
	 * @return
	 */
	protected static String buildOrderBy(LinkedHashMap<String, String> orderBy) {
		StringBuffer orderbyql = new StringBuffer();
		if(orderBy != null && orderBy.size() > 0) {
			orderbyql.append(" order by ");
			for(String key : orderBy.keySet()) {
				orderbyql.append(" o. ").append(key).append(" ").append(orderBy.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length() - 1);
		}
		return orderbyql.toString();
	}



}
