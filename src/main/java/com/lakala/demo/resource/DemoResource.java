package com.lakala.demo.resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lakala.demo.beans.RetCode;
import com.lakala.demo.config.Constant;
import com.lakala.demo.service.DemoService;
import com.lakala.demo.service.impl.DemoServiceImpl;
import com.lakala.demo.util.DateUtils;
import com.lakala.demo.util.JsonMapper;
import com.lakala.demo.util.ResultUtil;
import com.lakala.demo.util.SpringUtils;

/**
 * 类名称：DemoResource
 * 类描述：(实例)
 * 创建人：litj
 * 创建时间：2013-11-1 下午01:06:05
 * 修改人：
 * 修改时间：2013-11-1 下午01:06:05
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Path("/mysql/user/")
@Component
public class DemoResource {
	protected static Logger logger = LoggerFactory.getLogger(DemoResource.class);

	private static JsonMapper binder = JsonMapper.nonEmptyMapper();
	@Autowired
	private DemoService demoService;

	public DemoResource() {
		demoService = SpringUtils.getBean(DemoServiceImpl.class);
	}

	@POST
	@Path("isOpen")
	@Consumes
	@Produces(Constant.JSON_UTF_8)
	public String isOpen(@FormParam("PSAM") String PSAM) {

		String param = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("PSAM", PSAM);
		map.put("OP_TIME", DateUtils.date24ToString(new Date()));

		param = binder.toJson(map);
		logger.info("\n==isOpen()==param==" + param);

		RetCode response = demoService.isOpen(param);

		if (response == null) {
			Map<String, Object> result = ResultUtil.getResult(Constant.DEMO_ISOPEN_ERROR_CODE, Constant.DEMO_ISOPEN_ERROR_MSG);
			return binder.toJson(result);
		} else {
			return binder.toJson(ResultUtil.getResult(response));
		}
	}

	// @POST
	// @Path("create")
	// @Produces(Constant.JSON_UTF_8)
	// public String create(MultivaluedMap<String, String> formMap) throws JSONException {
	//
	// String param = null;
	// if (!CollectionUtils.sizeIsEmpty(formMap)) {
	// param = formMap.getFirst("param");
	// }
	// logger.info("\n==create()==" + param);
	//
	// RetCode response = demoService.create(param);
	//
	// if (null != response && "true".equals(response.getSuccess())) {
	// logger.info("终端开通成功");
	// // 终端开通成功
	// response.setStep("1");
	// // 开通收单
	// JSONObject json = JSONObject.fromObject(param);
	// if (json.containsKey("shopNo") && null != json.get("shopNo") && !"".equals(json.getString("shopNo"))) {
	// logger.info("上送了商户号，进行收单开通");
	// RetCode responseShouDan = null;
	// try {
	// responseShouDan = shouDanService.openShouDan(json);
	// if (!"true".equals(responseShouDan.getSuccess())) {
	// throw new ServiceException(Constant.ERROR_CODE_5005, Constant.ERROR_MSG_5005 + ":"
	// + responseShouDan.getInfo());
	// }
	// response.setStep("2");
	// } catch (ServiceException se) {
	// logger.info("ServiceException", se);
	// response.setSuccess("false");
	// response.setMsg(se.getErrorMsg());
	// return binder.toJson(ResultUtil.getResult(response));
	// } catch (Exception e) {
	// logger.info(e.getClass().getName(), e);
	// response.setSuccess("false");
	// response.setMsg("系统异常:" + e.getClass().getName());
	// return binder.toJson(ResultUtil.getResult(response));
	//
	// }
	// }
	// } else if (null != response && !"true".equals(response.getSuccess())) {
	// logger.info("终端开通失败");
	// // 终端开通失败
	// response.setStep("0");
	// }
	//
	// if (response == null) {
	// Map<String, Object> result = ResultUtil.getResult(Constant.DEMO_CREATE_ERROR_CODE, Constant.DEMO_CREATE_ERROR_MSG);
	// return binder.toJson(result);
	// } else {
	// return binder.toJson(ResultUtil.getResult(response));
	// }
	// }
}
