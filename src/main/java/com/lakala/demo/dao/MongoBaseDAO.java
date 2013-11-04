package com.lakala.demo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.query.Query;
import com.github.jmkgreen.morphia.query.UpdateOperations;
import com.lakala.demo.util.LKLConfig;
import com.lakala.demo.util.MongoDBConnection;

/**
 * 
 * 类名称：MongoBaseDAO
 * 类描述：(Mongodb的CRUD工具类)
 * 创建人：litj
 * 创建时间：2013-7-9 上午09:49:29
 * 修改人：
 * 修改时间：2013-7-9 上午09:49:29
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Component
public class MongoBaseDAO<T> {

	private Datastore ds = null;
	private String mongoName = LKLConfig.getValue("mongoDBName");

	public MongoBaseDAO() {
		ds = MongoDBConnection.getDataStore(mongoName);
		ds.ensureIndexes();
		ds.ensureCaps();
	}

	public void add(T t) {
		ds.save(t);
	}

	public void delete(T t) {
		ds.delete(t);
	}

	public void delete(Query<T> query) {
		ds.delete(query);
	}

	public int update(T t, UpdateOperations<T> op) {
		return ds.update(t, op).getUpdatedCount();
	}

	public void update(Query<T> query, UpdateOperations<T> op) {
		ds.update(query, op);
	}

	public List<T> query(Query<T> query) {
		return query.asList();
	}

	public long count(Query<T> query) {
		return query.countAll();
	}

	public Datastore getDS() {
		return ds;
	}
}
