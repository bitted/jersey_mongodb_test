package com.lakala.demo.service;

import java.util.List;

import com.lakala.demo.beans.generated.User;
import com.lakala.demo.exception.ServiceException;

/**
 * 类名称：UserService
 * 类描述：(用户接口)
 * 创建人：litj
 * 创建时间：2013-11-1 下午01:45:41
 * 修改人：
 * 修改时间：2013-11-1 下午01:45:41
 * 修改备注：
 * 
 * @version 1.0.0
 */
public interface UserService {

	public void insert(User user) throws ServiceException;

	public void delete(Integer id) throws ServiceException;

	public void update(User user) throws ServiceException;

	public User query(Integer id) throws ServiceException;

	public List<User> query(User user) throws ServiceException;
}
