package com.lakala.demo.util;

import java.util.HashMap;
import java.util.Map;

import com.lakala.demo.beans.RetStatus;
import com.lakala.demo.config.Constant;
import com.lakala.demo.exception.ServiceException;

/**
 * 类名称：ResultUtil
 * 类描述：(返回结果的工具类)
 * 创建人：litj
 * 创建时间：2013-9-1 上午10:46:09
 * 修改人：
 * 修改时间：2013-9-1 上午10:46:09
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class ResultUtil {

	/**
	 * getResult(返回错误code和msg)
	 * 
	 * @param retCode
	 * @param errMsg
	 * @return
	 *         返回类型：Map<String,Object>
	 * @exception
	 * @since 1.0.0
	 */
	public static Map<String, Object> getResult(String retCode, String errMsg) {
		RetStatus retStatus = new RetStatus(retCode, errMsg);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("retStatus", retStatus);

		return res;
	}

	/**
	 * getResult(返回一个对象)
	 * 
	 * @param resData
	 * @return
	 *         返回类型：Map<String,Object>
	 * @exception
	 * @since 1.0.0
	 */
	public static Map<String, Object> getResult(Object resData) {
		RetStatus retStatus = null;
		// if (resData instanceof RetCode && StringUtils.equals("false", ((RetCode) resData).getSuccess())) {
		// retStatus = new RetStatus(((RetCode) resData).getSuccess(), ((RetCode) resData).getMsg());
		// } else {
		retStatus = new RetStatus(Constant.DEMO_RETURN_CODE_SUCCESS, Constant.DEMO_RETURN_MSG_SUCCESS);
		// }
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("retStatus", retStatus);
		res.put("retData", resData);

		return res;
	}

	public static Map<String, Object> dealServiceException(ServiceException se) {
		RetStatus retStatus = new RetStatus(se.getErrorCode(), se.getErrorMsg());
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("retStatus", retStatus);
		return res;
	}
}
