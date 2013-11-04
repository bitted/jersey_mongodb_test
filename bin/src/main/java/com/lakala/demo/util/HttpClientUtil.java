/**
 * 
 */
package com.lakala.demo.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类名称：HttpClientUtil
 * 类描述：(http请求util)
 * (这里描述此类业务作用，描述性说明 ----必填）
 * 创建人：litj
 * 创建时间：2013-8-29 上午11:04:03
 * 修改人：
 * 修改时间：2013-8-29 上午11:04:03
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class HttpClientUtil {

	protected static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	private static HttpClient httpClient = null;
//	// 定义一个用于放置数据库连接的局部线程变量（使每个线程都拥有自己的连接）
//	private static ThreadLocal<Integer> timer = new ThreadLocal<Integer>() {
//		@Override
//		protected Integer initialValue() {
//			return 2;
//		}
//	};
//
//	public static int getTimer() {
//		timer.set(timer.get() - 1);
//		return timer.get();
//	}

	public synchronized static HttpClient getHttpClient() {
		if (httpClient == null && !(httpClient instanceof HttpClient)) {
			MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
			httpClient = new HttpClient(connectionManager);
			// 最大连接数
			// connectionManager.setMaxConnectionsPerHost(100);
			httpClient.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(300);
			// 最大活动连接数
			// connectionManager.setMaxTotalConnections(250);
			httpClient.getHttpConnectionManager().getParams().setMaxTotalConnections(500);
			httpClient.getParams().setContentCharset(HTTP.UTF_8);
			httpClient.getParams().setVersion(org.apache.commons.httpclient.HttpVersion.HTTP_1_1);
			// 设置 Http 连接超时为5秒
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			// 请求超时为 10秒
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
			// 设置从HttpConnectionManager中恢复HttpConnection所需要的毫秒时间
			httpClient.getParams().setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, Long.valueOf(30000));
			// 设置重试次数
			httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2/* 2次重试机会 */, true));
		}
		return httpClient;
	}

	/**
	 * 连接测试接口 beat heart
	 * 
	 * @param 通信测试地址
	 *            <strong>url<strong>
	 * @return httpStatus <strong>status=200为正常通信！</strong>
	 */
	public static int testHTTPConnection(String url) {
		logger.info("==into testHTTPConnection==");
		int status = 555;
		PostMethod postMethod = new PostMethod(url);
		HttpClient client = HttpClientUtil.getHttpClient();
		try {
			status = client.executeMethod(postMethod);
			if (status != HttpStatus.SC_OK) {
				throw new HttpException("电子货架Http通信异常！请尽快联系作业系统！！！");
			}
		} catch (HttpException e) {
			logger.warn("【url=" + url + "】【httpStatus=" + status + "】电子货架接口--HTTP连接出现异常！！！:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.warn("【url=" + url + "】【httpStatus=" + status + "】电子货架接口--连接出现IO异常！！！:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.warn("【url=" + url + "】【httpStatus=" + status + "】电子货架接口--测试通信中系统异常！！！：" + e.getMessage());
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
			return status;
		}
	}

	public static void main(String[] args) {
		int status = testHTTPConnection(LKLConfig.getValue("lkl.es.isOpen"));
		System.out.println(status);
	}

	/**
	 * 处理httpPost请求，此处为通信异常
	 * 
	 * @param postMethod
	 * @return
	 */
	public static String httpPostRequest(PostMethod postMethod) throws ConnectTimeoutException, HttpException, IOException {
		String response = null;
		int status = HttpStatus.SC_INTERNAL_SERVER_ERROR;
		HttpClient client = HttpClientUtil.getHttpClient();

//		logger.info("==发送httpPostRequest==请求http连接====client：" + client + ",timer=" + timer);
		// 执行postMethod
		try {
			status = client.executeMethod(postMethod);
			logger.info(status + "--------状态------------");
			if (status != HttpStatus.SC_OK) {
				// 失败处理5
				logger.warn("--------------http request Failed httpStatus" + status + "-----------------");
				throw new Exception("http请求失败!");
			} else if (status == HttpStatus.SC_OK) {
				logger.info("-------------Http 通信成功-----------------");
				response = postMethod.getResponseBodyAsString();
				logger.info("---------服务器返回值---------:" + response);
			}

		} catch (Exception e) {
			logger.warn("-----------通信异常---------"/* , e */);
		} finally {
			// http通信请求返回信息
			logger.info("Http status=" + status + ",Response:" + response);
//			// 处理返回500的情况
//			if (status == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
//				// int stat = testHTTPConnection(LKLConfig.getValue("lkl.es.isOpen"));
//				// logger.error("==status=" + stat + ",timer=" + timer);
//				logger.error("timer=" + timer.get());
//				if (timer.get() > 0) {
//					getTimer();
//					httpPostRequest(postMethod);
//				} else {
//					response = null;// Constant.RESPONSE_MSG;//不处理500异常
//					// throw new HttpException("电子货架Http通信异常！请尽快联系作业系统！！！");
//				}
//			}
			postMethod.releaseConnection();// 释放连接
		}
		return response;
	}

	/**
	 * Http Post 请求
	 * 
	 * @param <T>
	 * @param url
	 *            请求地址
	 * @param requestBody
	 * @return T
	 * @throws HttpException
	 * @throws IOException
	 */
	public static <T> T httpPostParam(String url, String interfaceName, String param) throws Exception {
		logger.info("===电子货架-" + interfaceName + "==请求URL： " + url);
		String response = null;

		// POST 请求
		PostMethod postMethod = new PostMethod(url);
		postMethod.addParameter("param", param);
		response = httpPostRequest(postMethod);
		return (T) response;
	}

	public static <T> T httpPostParamByUrl(String url, String interfaceName, String param) throws Exception {
		logger.info("===电子货架-" + interfaceName + "==请求URL： " + url);
		String response = null;
		// POST 请求
		PostMethod postMethod = new PostMethod(url);
		response = httpPostRequest(postMethod);
		return (T) response;
	}
	
	public static <T> T httpPostParam(String url, String interfaceName, Map<String,String> params) throws Exception {
		logger.info("===电子货架-" + interfaceName + "==请求URL： " + url);
		String response = null;

		// POST 请求
		PostMethod postMethod = new PostMethod(url);
		
		for(Iterator<String> it = params.keySet().iterator();it.hasNext();){
			String key = it.next();
			postMethod.addParameter(key, params.get(key));
		}
		response = httpPostRequest(postMethod);
		return (T) response;
	}
}
