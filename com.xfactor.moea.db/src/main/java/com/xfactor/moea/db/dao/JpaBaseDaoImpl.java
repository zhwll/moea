package com.xfactor.moea.db.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("jpaBaseDao")
public class JpaBaseDaoImpl implements IJpaBaseDao {

	
	private EntityManager entityManager;
	
	/**
	 * 如果不是defaultEntityManager,可以覆盖此set方法
	 * @param defaultEm
	 */
	@Autowired(required=false)
	@PersistenceContext(unitName = "defaultEntityManager")
	public void setDefaultEm(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	protected EntityManager getEm(){
		return this.entityManager;
	}
	
	
	public<T,ID extends Serializable> T getObj(Class<T> cls,ID id) {
		return this.getEm().find(cls, id);
	}
	
	public <T> List<T> findAllObj(Class<T> cls) {
		CriteriaQuery<T> query = this.getEm().getCriteriaBuilder().createQuery(cls);
		return this.getEm().createQuery(query.select(query.from(cls))).getResultList();
	}
	
	public <T> void saveObj(T t) {
		this.getEm().persist(t);
	}
 
	/**
	 * 修改
	 * @param t 实体对象
	 * @return
	 */
	public <T> T updateObj(T t) {
		return this.getEm().merge(t);
	}
 
	/**
	 * 删除
	 * @param t
	 */
	public <T> void deleteObj(T t) {
		this.getEm().remove(t);
	}
	
	
	
	
	
	/**
	 * 
	 * 根据实体中不为空字段查询数据库中的数量
	 * 
	 * @param t 查询条件
	 * @return 数据库数量
	 */
	public <T> Integer queryCount(T t) {
		try {
			//创建查询器
			CriteriaBuilder builder = this.getEm().getCriteriaBuilder();
			CriteriaQuery<Long> query = builder.createQuery(Long.class);
			Root<T> root = query.from((Class<T>)t.getClass());
			
			//创建查询条件集合
			List<Predicate> paramList =new  ArrayList<Predicate>();
			
			//反向映射
			Field[] fields = t.getClass().getDeclaredFields();
			for (Field field:fields) {
				if(java.lang.reflect.Modifier.isFinal(field.getModifiers())){
					continue;
				}
				Field.setAccessible(fields, true);
				Object object = field.get(t);
				if(object!=null){
					//添加查询条件
					paramList.add(builder.equal(root.get(field.getName()).as(field.getType()), object));
				}
			}
			query.where(paramList.toArray(new Predicate[paramList.size()]));
			
			//返回查询到的结果
			return this.getEm().createQuery(query.select(builder.count(root))).getSingleResult().intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
 
	
	
	
	
	/**
	 * 
	 * 根据实体中不为空字段分页查询得到List集合,此方式不能获取记录条数和页数
	 * @param t 查询条件
	 * @param startRow 分页开始位置
	 * @param pageSize 分页结束位置
	 * @return
	 */
	public <T> List<T> queryPageList(T t,int startRow,int pageSize) {
		try {
			//创建查询器
			CriteriaBuilder builder = this.getEm().getCriteriaBuilder();
			CriteriaQuery<T> query = builder.createQuery((Class<T>)t.getClass());
			Root<T> root = query.from((Class<T>)t.getClass());
			
			//创建查询条件集合
			List<Predicate> paramList =new  ArrayList<Predicate>();
			
			//反向映射
			Field[] fields = t.getClass().getDeclaredFields();
			for (Field field:fields) {
				if(java.lang.reflect.Modifier.isFinal(field.getModifiers())){
					continue;
				}
				Field.setAccessible(fields, true);
				Object object = field.get(t);
				if(object!=null){
					//添加查询条件
					paramList.add(builder.equal(root.get(field.getName()).as(field.getType()), object));
				}
			}
			query.where(paramList.toArray(new Predicate[paramList.size()]));
			//返回查询到的结果集
			return this.getEm().createQuery(query.select(root)).setFirstResult(startRow).setMaxResults(pageSize).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据hql查询数据库
	 * @param hql hql
	 * @param cls 需要查询的entity
	 * @param args 查询参数
	 * @return 查询的结果集
	 */
	public<T> List<T> findByHql(String hql,Class<T> cls,Object ...args) {
		TypedQuery<T> createQuery = this.getEm().createQuery(hql,cls);
		if(args!=null&&args.length>0){
			for (int i = 0;i < args.length; i++) {
				createQuery.setParameter((i+1), args[i]);
			}
		}
		return createQuery.getResultList();
	}
	
	
	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Object> findByHql(String hql,Object ...args) {
		Query createQuery = (Query) this.getEm().createQuery(hql);
		if(args!=null&&args.length>0){
			for (int i = 0;i < args.length; i++) {
				createQuery.setParameter((i+1), args[i]);
			}
		}
		return createQuery.getResultList();
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Map<String,Object>> findMapByHql(String hql,Object ...args) {
		Query createQuery = (Query) this.getEm().createQuery(hql);
		createQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		if(args!=null&&args.length>0){
			for (int i = 0;i < args.length; i++) {
				createQuery.setParameter((i+1), args[i]);
			}
		}
		return createQuery.getResultList();
	}
	@SuppressWarnings("unchecked")
	public<T> List<T> findBySql(String sql,Class<T> cls,Object ...args) {
		// TODO Auto-generated method stub
		Query createQuery = (Query) this.getEm().createNativeQuery(sql,cls);
		if(args!=null&&args.length>0){
			for (int i = 0;i < args.length; i++) {
				createQuery.setParameter((i+1), args[i]);
			}
		}
		return createQuery.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> findBySql(String sql,Object ...args) {
		// TODO Auto-generated method stub
		Query createQuery = (Query) this.getEm().createNativeQuery(sql);
		if(args!=null&&args.length>0){
			for (int i = 0;i < args.length; i++) {
				createQuery.setParameter((i+1), args[i]);
			}
		}
		
		return createQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findMapBySql(String sql,Object ...args) {
		// TODO Auto-generated method stub
		Query createQuery = (Query) this.getEm().createNativeQuery(sql);
		createQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		if(args!=null&&args.length>0){
			for (int i = 0;i < args.length; i++) {
				createQuery.setParameter((i+1), args[i]);
			}
		}
		return createQuery.getResultList();
	}


	@Override
	public List<Map<String, Object>> findPageMapByHql(String hql, int startrow, int pagesize, Object... args) {
		// TODO Auto-generated method stub
		startrow = startrow<=0?0:startrow;
		pagesize = pagesize<=0?10:pagesize;
		Query createQuery = (Query) this.getEm().createQuery(hql);
		createQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		if(args!=null&&args.length>0){
			for (int i = 0;i < args.length; i++) {
				createQuery.setParameter((i+1), args[i]);
			}
		}
		return createQuery.setFirstResult(startrow).setMaxResults(pagesize).getResultList();
	}


	@Override
	public List<Map<String, Object>> findPageMapBySql(String sql, int startrow, int pagesize, Object... args) {
		// TODO Auto-generated method stub
		startrow = startrow<=0?0:startrow;
		pagesize = pagesize<=0?10:pagesize;
		Query createQuery = (Query) this.getEm().createNativeQuery(sql);
		createQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		if(args!=null&&args.length>0){
			for (int i = 0;i < args.length; i++) {
				createQuery.setParameter((i+1), args[i]);
			}
		}
		return createQuery.setFirstResult(startrow).setMaxResults(pagesize).getResultList();
	}
	 

}
