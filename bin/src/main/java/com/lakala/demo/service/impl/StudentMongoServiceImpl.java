/**
 * 项目名称：拉卡拉演出票接口项目
 * 包名：com.lakala.demo.service.impl
 * 文件名：UserServiceImpl.java
 * 版本信息：V1.0
 * 日期：2013-11-1-下午01:49:15
 * 作者：litj
 * Copyright (c) 2013拉卡拉支付有限公司-版权所有
 */

package com.lakala.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jmkgreen.morphia.query.Query;
import com.github.jmkgreen.morphia.query.UpdateOperations;
import com.google.common.collect.Lists;
import com.lakala.demo.beans.generated.Student;
import com.lakala.demo.dao.StudentMongoDao;
import com.lakala.demo.exception.ServiceException;
import com.lakala.demo.service.StudentMongoService;
import com.mongodb.WriteConcern;

/**
 * 类名称：UserMongoServiceImpl
 * 类描述：(User的mongo实现)
 * 创建人：litj
 * 创建时间：2013-11-1 下午01:49:15
 * 修改人：
 * 修改时间：2013-11-1 下午01:49:15
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Component
public class StudentMongoServiceImpl implements StudentMongoService {
	protected static Logger logger = LoggerFactory.getLogger(StudentMongoServiceImpl.class);

	@Autowired
	StudentMongoDao dao;

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.UserService#insert(com.lakala.demo.beans.generated.User)
	 */

	@Override
	public void insert(Student student) throws ServiceException {
		dao.add(student);
		logger.info("===保存成功！===");
	}

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.UserService#delete(java.lang.Long)
	 */

	@Override
	public void delete(ObjectId id) throws ServiceException {

		logger.info("===delete()===");
		Student student = new Student();
		student.setId(id);

		dao.delete(student);
		logger.info("===删除成功！===");
	}

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.UserService#update(com.lakala.demo.beans.generated.User)
	 */

	@Override
	public void merge(Student student) throws ServiceException {

		dao.getDS().merge(student);
		logger.info("===更新成功！===");

	}

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.StudentMongoService#update(com.lakala.demo.beans.generated.Student)
	 */
	public void update(Student student) throws ServiceException {
		Query<Student> query = this.dao.getDS().createQuery(Student.class);
		query.field("loginName").equal(student.getLoginName());

		UpdateOperations<Student> up = this.dao.getDS().createUpdateOperations(Student.class);

		up.set("name", student.getName()).set("updateDate", student.getUpdateDate());

		dao.getDS().update(query, up, false, WriteConcern.SAFE);
		logger.info("===更新成功！===");

	}

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.StudentMongoService#updateBetch(com.lakala.demo.beans.generated.Student)
	 */
	public void updateBetch(Student student) throws ServiceException {
		Query<Student> query = this.dao.getDS().createQuery(Student.class);
		query.field("loginName").contains(student.getLoginName());

		System.out.println("====符合条件的记录数为：" + query.asList().size());
		UpdateOperations<Student> up = this.dao.getDS().createUpdateOperations(Student.class);

		up.set("name", student.getName()).set("updateDate", student.getUpdateDate());

		dao.getDS().update(query, up, false, WriteConcern.SAFE);
		logger.info("===更新成功！===");

	}

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.UserService#query(java.lang.Long)
	 */

	@Override
	public Student query(ObjectId id) throws ServiceException {
		Student student = dao.getDS().get(Student.class, id);
		logger.info("===user===" + student.toString());

		return student;
	}

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.UserService#query(com.lakala.demo.beans.generated.User)
	 */

	@Override
	public List<Student> query(Student student) throws ServiceException {
		List<Student> studentList = Lists.newArrayList();

		Query<Student> query = this.dao.getDS().createQuery(Student.class);
		query.field("name").contains(student.getName());
		studentList = dao.query(query);
		logger.info("===返回成功！===");
		return studentList;
	}

	
	/* (non-Javadoc)
	 * @see com.lakala.demo.service.StudentMongoService#findAll(int, int)
	 */
	@Override
	public List<Student> findAll(int start, int end) throws ServiceException {
		return dao.findAll(start, end);
	}

}
