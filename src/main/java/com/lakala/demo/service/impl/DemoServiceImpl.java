/**
 * 项目名称：电子货架接口项目
 * 包名：com.lakala.demo.service.impl
 * 文件名：ESServiceImpl
 * 版本信息：V1.0
 * 日期：2013年9月7日10:44:04
 * 作者：litj
 * Copyright (c) 2013拉卡拉支付有限公司-版权所有
 */
package com.lakala.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lakala.demo.beans.RetCode;
import com.lakala.demo.config.Constant;
import com.lakala.demo.service.DemoService;
import com.lakala.demo.util.HttpClientUtil;
import com.lakala.demo.util.JsonMapper;
import com.lakala.demo.util.LKLConfig;
import com.lakala.demo.util.StringUtils;

/**
 * 类名称：DemoServiceImpl
 * 类描述：(实现类)
 * 创建人：litj
 * 创建时间：2013-11-1 下午01:12:36
 * 修改人：
 * 修改时间：2013-11-1 下午01:12:36
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Component
public class DemoServiceImpl implements DemoService {

	protected static Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);
	private static JsonMapper binder = JsonMapper.nonEmptyMapper();

	/*
	 * (non-Javadoc)
	 * @see com.lakala.demo.service.ESService#isOpen(java.lang.String)
	 */
	@Override
	public RetCode isOpen(String param) {
		/**
		 * 1、统一返回的response
		 */
		RetCode response = null;
		/**
		 * 2、获取请求地址
		 */
		String url = LKLConfig.getValue("demo.isOpen");

		try {
			/**
			 * 3、开始通信并返回处理后消息bean
			 */
			String respString = HttpClientUtil.httpPostParam(url, Constant.IS_OPEN, param);
			/**
			 * 4、返回的报文转化成需要的Bean
			 */
			if (!StringUtils.isEmpty(respString)) {
				response = binder.fromJson(respString, RetCode.class);
			}

		} catch (Exception e) {
			logger.warn("===" + Constant.IS_OPEN + "===" + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
}
