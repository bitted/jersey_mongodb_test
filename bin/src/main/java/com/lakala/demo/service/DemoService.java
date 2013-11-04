package com.lakala.demo.service;

import com.lakala.demo.beans.RetCode;


/**
 * 类名称：DemoService
 * 类描述：()
 * 创建人：litj
 * 创建时间：2013-11-1 下午01:17:34
 * 修改人：
 * 修改时间：2013-11-1 下午01:17:34
 * 修改备注：
 * @version 1.0.0
 */
public interface DemoService {

	/**
	 * isOpen(终端是否开通)
	 * 
	 * @param param
	 * @return
	 *         返回类型：RetCode
	 * @exception
	 * @since 1.0.0
	 */
	public RetCode isOpen(String param);
}