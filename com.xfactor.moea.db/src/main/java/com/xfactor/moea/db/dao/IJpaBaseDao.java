package com.xfactor.moea.db.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface IJpaBaseDao {

	
	
	
	public<T,ID extends Serializable> T getObj(Class<T> cls,ID id);
	public <T> List<T> findAllObj(Class<T> cls);
	public <T> void saveObj(T t);
	public <T> T updateObj(T t);
	public <T> void deleteObj(T t);
	public <T> Integer queryCount(T t);
	public <T> List<T> queryPageList(T t,int startRow,int pageSize);
	
	public<T> List<T> findByHql(String hql,Class<T> cls,Object ...args);
	public List<Object> findByHql(String hql,Object ...args);
	public List<Map<String,Object>> findMapByHql(String hql,Object ...args);
	public List<Map<String,Object>> findPageMapByHql(String hql,int startrow,int pagesize,Object ...args);
	
	public<T> List<T> findBySql(String sql,Class<T> cls,Object ...args);
	public List<Object> findBySql(String sql,Object ...args);
	public List<Map<String,Object>> findMapBySql(String sql,Object ...args);
	public List<Map<String,Object>> findPageMapBySql(String sql,int startrow,int pagesize,Object ...args);
}
