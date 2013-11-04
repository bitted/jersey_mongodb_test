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

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.lakala.demo.beans.generated.User;
import com.lakala.demo.beans.generated.UserExample;
import com.lakala.demo.dao.generated.UserDAO;
import com.lakala.demo.exception.ServiceException;
import com.lakala.demo.service.UserService;

/**
 * 类名称：UserServiceImpl
 * 类描述：(User实现)
 * 创建人：litj
 * 创建时间：2013-11-1 下午01:49:15
 * 修改人：
 * 修改时间：2013-11-1 下午01:49:15
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class UserServiceImpl implements UserService {
	protected static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDAO userDao;

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.UserService#insert(com.lakala.demo.beans.generated.User)
	 */

	@Override
	public void insert(User user) throws ServiceException {
		try {
			userDao.insert(user);
			logger.info("===保存成功！===");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.UserService#delete(java.lang.Long)
	 */

	@Override
	public void delete(Integer id) throws ServiceException {

		UserExample example = new UserExample();
		example.createCriteria().andIdEqualTo(id);
		try {
			userDao.deleteByExample(example);
			logger.info("===删除成功！===");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.UserService#update(com.lakala.demo.beans.generated.User)
	 */

	@Override
	public void update(User user) throws ServiceException {
		List<User> userList = Lists.newArrayList();
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(user.getName());

		try {
			userDao.updateByExample(user, example);
			logger.info("===更新成功！===");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.UserService#query(java.lang.Long)
	 */

	@Override
	public User query(Integer id) throws ServiceException {
		User user = null;
		try {
			user = userDao.selectByPrimaryKey(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("===user===" + user.toString());

		return user;
	}

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.UserService#query(com.lakala.demo.beans.generated.User)
	 */

	@Override
	@SuppressWarnings("unchecked")
	public List<User> query(User user) throws ServiceException {
		List<User> userList = Lists.newArrayList();
		UserExample example = new UserExample();
		example.createCriteria().andNameLike("a");

		try {
			userList = userDao.selectByExample(example);
			logger.info("===返回成功！===");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

}
