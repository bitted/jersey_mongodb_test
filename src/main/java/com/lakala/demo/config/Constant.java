/**
 * 项目名称：Demo接口项目
 * 包名：com.lakala.demo.config
 * 文件名：Constant
 * 版本信息：V1.0
 * 日期：2013年9月7日10:44:04
 * 作者：litj
 * Copyright (c) 2013拉卡拉支付有限公司-版权所有
 */
package com.lakala.demo.config;

/**
 * 类名称：Constant
 * 类描述：(常量类)
 * 创建人：litj
 * 创建时间：2013-9-7 上午11:01:14
 * 修改人：
 * 修改时间：2013-9-7 上午11:01:14
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class Constant {

	/**
	 * 返回状态码定义
	 */
	public static final String DEMO_RETURN_CODE_SUCCESS = "0000"; // 成功code
	public static final String DEMO_RETURN_MSG_SUCCESS = "成功";// 成功提示
	/**
	 * MediaType定义
	 */
	public final static String APPLICATION_XML = "application/xml";
	public final static String APPLICATION_XML_UTF_8 = "application/xml; charset=UTF-8";

	public final static String JSON = "application/json";
	public final static String JSON_UTF_8 = "application/json; charset=UTF-8";

	public final static String TEXT_PLAIN = "text/plain";
	public final static String TEXT_PLAIN_UTF_8 = "text/plain; charset=UTF-8";

	/**
	 * 设置HttpClient超时时间
	 */
	public static final Long HTTP_CONNECTION_TIMEOUT = 600000L;// 请求超时10分钟
	public static final Long HTTP_SO_TIMEOUT = 600000L;// 读取超时10分钟
	public static final Long HTTP_CONNECTION_MANAGER_TIMEOUT = 500000L;// 设置从HttpConnectionManager中恢复HttpConnection所需要的毫秒时间
																		// 500返回消息！
	/**
	 * Demo错误返回处理
	 */
	/** ====================================Demo错误返回CODE==================================================== */
	public static final String DEMO_ISOPEN_ERROR_CODE = "10001";
	/** ====================================Demo错误返回消息==================================================== */
	public static final String DEMO_ISOPEN_ERROR_MSG = "终端是否开通请求异常！";
	/** ====================================Demo错误Log消息==================================================== */
	public static final String IS_OPEN = "校验终端是否开通接口";
}