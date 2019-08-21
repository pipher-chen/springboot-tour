package com.example.demo.mongodb.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Component
public class MongoUtils<T> {

    public static MongoUtils mongoUtils;

    @PostConstruct
    public void init() {
        mongoUtils = this;
        mongoUtils.mongoTemplate = this.mongoTemplate;
    }

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 保存数据对象，集合为数据对象中@Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     */
    public static void save(Object obj) {

        mongoUtils.mongoTemplate.save(obj);
    }

    /**
     * 指定集合保存数据对象
     *
     * @param obj
     *            数据对象
     * @param collectionName
     *            集合名
     */
    public static void save(Object obj, String collectionName) {

        mongoUtils.mongoTemplate.save(obj, collectionName);
    }

    /**
     * 根据数据对象中的id删除数据，集合为数据对象中@Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     */
    public static void remove(Object obj) {

        mongoUtils.mongoTemplate.remove(obj);
    }

    /**
     * 指定集合 根据数据对象中的id删除数据
     *
     * @param obj
     *            数据对象
     * @param collectionName
     *            集合名
     */
    public static void remove(Object obj, String collectionName) {

        mongoUtils.mongoTemplate.remove(obj, collectionName);
    }

    /**
     * 根据key，value到指定集合删除数据
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param collectionName
     *            集合名
     */
    public static void removeById(String key, Object value, String collectionName) {

        Criteria criteria = Criteria.where(key).is(value);
        criteria.and(key).is(value);
        Query query = Query.query(criteria);
        mongoUtils.mongoTemplate.remove(query, collectionName);
    }

    /**
     * 指定集合 修改数据，且仅修改找到的第一条数据
     *
     * @param accordingKey
     *            修改条件 key
     * @param accordingValue
     *            修改条件 value
     * @param updateKeys
     *            修改内容 key数组
     * @param updateValues
     *            修改内容 value数组
     * @param collectionName
     *            集合名
     */
    public static void updateFirst(String accordingKey, Object accordingValue, String[] updateKeys, Object[] updateValues,
                                   String collectionName) {

        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (int i = 0; i < updateKeys.length; i++) {
            update.set(updateKeys[i], updateValues[i]);
        }
        mongoUtils.mongoTemplate.updateFirst(query, update, collectionName);
    }

    /**
     * 指定集合 修改数据，且修改所找到的所有数据
     *
     * @param accordingKey
     *            修改条件 key
     * @param accordingValue
     *            修改条件 value
     * @param updateKeys
     *            修改内容 key数组
     * @param updateValues
     *            修改内容 value数组
     * @param collectionName
     *            集合名
     */
    public static void updateMulti(String accordingKey, Object accordingValue, String[] updateKeys, Object[] updateValues,
                                   String collectionName) {

        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (int i = 0; i < updateKeys.length; i++) {
            update.set(updateKeys[i], updateValues[i]);
        }
        mongoUtils.mongoTemplate.updateMulti(query, update, collectionName);
    }

    /**
     * 根据条件查询出所有结果集 集合为数据对象中@Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @return
     */
    public static List<? extends Object> find(Object obj, String[] findKeys, Object[] findValues) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        List<? extends Object> resultList = mongoUtils.mongoTemplate.find(query, obj.getClass());
        return resultList;
    }

    /**
     * 指定集合 根据条件查询出所有结果集
     *
     * @param obj
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @param collectionName
     *            集合名
     * @return
     */
    public static List<? extends Object> find(Object obj, String[] findKeys, Object[] findValues, String collectionName) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        List<? extends Object> resultList = mongoUtils.mongoTemplate.find(query, obj.getClass(), collectionName);
        return resultList;
    }

    /**
     * 指定集合 根据条件查询出所有结果集 并排倒序
     *
     * @param obj
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @param collectionName
     *            集合名
     * @param sort
     *            排序字段
     * @return
     */
    public static List<? extends Object> find(Object obj, String[] findKeys, Object[] findValues, String collectionName ,String sort) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, sort));
        List<? extends Object> resultList = mongoUtils.mongoTemplate.find(query, obj.getClass(), collectionName);
        return resultList;
    }

    /**
     * 根据条件查询出符合的第一条数据 集合为数据对象中 @Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @return
     */
    public static Object findOne(Object obj, String[] findKeys, Object[] findValues) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        Object resultObj = mongoUtils.mongoTemplate.findOne(query, obj.getClass());
        return resultObj;
    }

    /**
     * 指定集合 根据条件查询出符合的第一条数据
     *
     * @param obj
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @param collectionName
     *            集合名
     * @return
     */
    public static Object findOne(Object obj, String[] findKeys, Object[] findValues, String collectionName) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        Object resultObj = mongoUtils.mongoTemplate.findOne(query, obj.getClass(), collectionName);
        return resultObj;
    }

    /**
     * 查询出所有结果集 集合为数据对象中 @Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     * @return
     */
    public static List<? extends Object> findAll(Object obj) {

        List<? extends Object> resultList = mongoUtils.mongoTemplate.findAll(obj.getClass());
        return resultList;
    }

    /**
     * 指定集合 查询出所有结果集
     *
     * @param obj
     *            数据对象
     * @param collectionName
     *            集合名
     * @return
     */
    public static List<? extends Object> findAll(Object obj, String collectionName) {

        List<? extends Object> resultList = mongoUtils.mongoTemplate.findAll(obj.getClass(), collectionName);
        return resultList;
    }

//    /**
//     * 分页查询
//     * @param page
//     * @param query
//     * @return
//     */
//    public Page<T> findPage(Page<T> page, Query query){
//        long count = this.count(query);
//        page.setTotal(count);
//        int pageNumber = page.getPagenumber();
//        int pageSize = page.getPageSize();
//        query.skip((pageNumber - 1) * pageSize).limit(pageSize);
//        List<T> rows = this.find(query);
//        page.setRows(rows);
//        return page;
//    }
//
//    public Page<T> findPage(Page<T> page, Query query, String collectionName){
//        long count = this.count(query, collectionName);
//        page.setTotal(count);
//        int pageNumber = page.getPagenumber();
//        int pageSize = page.getPageSize();
//        query.skip((pageNumber - 1) * pageSize).limit(pageSize);
//        List<T> rows = this.find(query, collectionName);
//        page.setRows(rows);
//        return page;
//    }

    /**
     * 求数据总和
     * @param query
     * @return
     */
    public long count(Query query){
        return mongoTemplate.count(query, this.getEntityClass());
    }

    public long count(Query query, String collectionName){
        return mongoTemplate.count(query, this.getEntityClass(), collectionName);
    }


    /**
     * 获取需要操作的实体类class
     *
     * @return
     */
    public Class<T> getEntityClass(){
        //Type superclass = this.getClass().getGenericSuperclass();
        //Type[] actualTypeArguments = ((ParameterizedType)superclass).getActualTypeArguments();
        // return (Class) actualTypeArguments[0];
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }



}
