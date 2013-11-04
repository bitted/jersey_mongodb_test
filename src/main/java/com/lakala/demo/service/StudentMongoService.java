package com.lakala.demo.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.lakala.demo.beans.generated.Student;
import com.lakala.demo.exception.ServiceException;

/**
 * 类名称：UserMongoService
 * 类描述：(mongo的CRUD)
 * 创建人：litj
 * 创建时间：2013-11-1 下午02:07:05
 * 修改人：
 * 修改时间：2013-11-1 下午02:07:05
 * 修改备注：
 * 
 * @version 1.0.0
 */
public interface StudentMongoService {

	public void insert(Student student) throws ServiceException;

	public void delete(ObjectId id) throws ServiceException;

	public void update(Student student) throws ServiceException;

	public void updateBetch(Student student) throws ServiceException;

	public void merge(Student student) throws ServiceException;

	public Student query(ObjectId id) throws ServiceException;

	public List<Student> query(Student student) throws ServiceException;

	public List<Student> findAll(int start, int end) throws ServiceException;

}