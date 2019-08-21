package com.example.demo.mongodb.service;

import com.example.demo.mongodb.common.Constant;
import com.example.demo.mongodb.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MongoBaseService<T> {
	
	
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 插入一条记录
	 * @param obj
	 */
	public void insert(T obj){
		this.mongoTemplate.insert(obj);
	}
	
	/**
	 * 根据查询语句一条记录，并删除记录
	 * @param query
	 */
	public boolean remove(Query query,Class<T> clazz){
		return this.mongoTemplate.remove(query, clazz).getDeletedCount() > 0  ? true : false;
	}
	
	/**
	 * 根据查询语句查询记录
	 * @param query
	 * @param clazz
	 * @return
	 */
	public List<T> find(Query query,Class<T> clazz){
		return this.mongoTemplate.find(query, clazz);				
	}
	
	/**
	 * 根据查询语句返回记录
	 * @param queryField 返回记录包含的字段
	 * @param query
	 * @param clazz
	 * @return
	 */
	public List<T> find(Set<String> queryField,Query query,Class<T> clazz){
		for(String field : queryField) {
			query.fields().include(field);
		}
		return this.mongoTemplate.find(query, clazz);
	}
	
	/**
	 * 查询单条记录
	 * @param query
	 * @param clazz
	 * @return
	 */
	public T findOne(Query query,Class<T> clazz){
		return this.mongoTemplate.findOne(query, clazz);
	}
	
	/**
	 * 查询单条记录
	 * @param queryField 返回记录包含的字段
	 * @param query
	 * @param clazz
	 * @return
	 */
	public T findOne(Set<String> queryField,Query query,Class<T> clazz){
		for(String field : queryField) {
			query.fields().include(field);
		}
		return this.mongoTemplate.findOne(query, clazz);
	}
	
	/**
	 * 根据条件查询并修改一条记录
	 * @param query
	 * @param update
	 * @param clazz
	 * @return
	 */
	public T findAndModify(Query query,Update update,Class<T> clazz){
		return this.mongoTemplate.findAndModify(query, update, clazz);
	}
	
	/**
	 * 根据查询记录数量
	 * @param query
	 * @param clazz
	 * @return
	 */
	public long count(Query query,Class<T> clazz){
		return this.mongoTemplate.count(query, clazz);
	}
	
	/**
	 * 根据条件查询并删除记录
	 * @param query
	 * @param clazz
	 * @return
	 */
	public List<T> findAndRemove(Query query,Class<T> clazz){
		return this.mongoTemplate.findAllAndRemove(query, clazz);
	}
	
	/**
	 * 更新首条记录
	 * @param query
	 * @param update
	 * @param clazz
	 * @return
	 */
	public boolean updateFirst(Query query,Update update,Class<T> clazz){
		return this.mongoTemplate.updateFirst(query, update, clazz).isModifiedCountAvailable();
	}
	
	/**
	 * 判断集合是否存在
	 * @param clazz
	 * @return
	 */
	public boolean collectionExist(Class<T> clazz){
		return this.mongoTemplate.collectionExists(clazz);
	}
	
	/**
	 * 创建一个集合
	 * @param clazz
	 */
	public void createCollection(Class<T> clazz){
		this.mongoTemplate.createCollection(clazz);
	}
	

	
	/**
	 * 批量更新
	 * @param query
	 * @param update
	 * @param clazz
	 * @return
	 */
	public boolean updateMulit(Query query,Update update,Class<T> clazz){
		return this.mongoTemplate.updateMulti(query, update, clazz).isModifiedCountAvailable();
	}
	
	
	/**
	 * 增加或更新
	 * @param obj
	 */
	public void save(Object obj){
		this.mongoTemplate.save(obj);
	}
	
	/**
	 * 判断记录是否存在
	 * @param query
	 * @param clazz
	 * @return
	 */
	public boolean exists(Query query,Class<T> clazz){
		return this.mongoTemplate.exists(query, clazz);
	}
	
	/**
	 * 更新或插入（不存在时插入）
	 * @param query
	 * @param update
	 * @param clazz
	 */
	public void upsert(Query query,Update update,Class<T> clazz){
		this.mongoTemplate.upsert(query, update, clazz);
	}
	
	/**
	 * 查找全部
	 * @param clazz
	 * @return
	 */
	public List<T> findAll(Class<T> clazz){
		return this.mongoTemplate.findAll(clazz);
	}
	
	/**
	 * 根据id得到对象
	 * @param clazz
	 * @param id
	 * @return
	 */
	public T get(Class<T> clazz,Long id){
		
		return this.mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), clazz);
	}
	
	/**
	 * 根据id更新
	 * @param clazz
	 * @param update
	 * @param id
	 */
	public void update(Class<T> clazz,Update update,Long id){
		Criteria criteria = Criteria.where("_id").is(id);
		Query query=new Query(criteria);
		
		this.mongoTemplate.updateMulti(query, update, clazz);
	}
	
	/**
	 * 插入一个集合
	 * @param objs
	 */
	public void insertAll(List<T> objs){
		this.mongoTemplate.insertAll(objs);
	}
	
	/**
	 * 根据id删除
	 * @param clazz
	 * @param id
	 */
	public void remove(Class<T> clazz,Long id){
		Criteria criteria = Criteria.where("_id").is(id);
		Query query=new Query(criteria);
		
		this.mongoTemplate.remove(query, clazz);
	}
	
	
	/**
	 * 通过条件查询,查询分页结果
	 */
	public Pagination<T> getPage(int pageNo, int pageSize, Query query, Class<T> clazz) {
		// 总页数
		if (pageNo < 1) {
			pageNo = Constant.PAGE_NO_DEFAULT;
		}
		if (pageSize <= 0 || pageSize > Constant.PAGE_SIZE_MAX) {
			pageSize = Constant.PAGE_SIZE_DEFAULT;
		}
		// 获取总条数
		long totalNumber = this.mongoTemplate.count(query, clazz);
		//int skip = (currentPage - pageSize) * pageSize;
		//query.skip(skip);// skip相当于从那条记录开始
		//query.limit(pageSize);// 从skip开始,取多少条记录
		Pageable pageable = new PageRequest(pageNo-1, pageSize);
		List<T> datas = new ArrayList<T>(pageSize);
		if (totalNumber > 0) {
			datas = this.mongoTemplate.find(query.with(pageable), clazz);
		}
		Pagination<T> p = new Pagination<T>(pageNo, pageSize, (int) totalNumber);
		// 获取数据
		p.build(datas);
		return p;
	}


}


























