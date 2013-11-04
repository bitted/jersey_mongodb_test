package com.lakala.demo.beans;

import java.util.List;

/**
 * 类名称：ResultData
 * 类描述：(返回给终端的对象)
 * 创建人：litj
 * 创建时间：2013-9-7 上午10:44:30
 * 修改人：
 * 修改时间：2013-9-7 上午10:44:30
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class ResultData {

	private RetStatus retStatus;
	private Object retData;

	public ResultData() {
	};

	public ResultData(Object retDate) {
		this.retStatus = new RetStatus("0", "success");
		this.retData = retDate;
	};

	public RetStatus getRetStatus() {
		return retStatus;
	}

	public void setRetStatus(RetStatus retStatus) {
		this.retStatus = retStatus;
	}

	public Object getRetData() {
		return retData;
	}

	public void setRetData(Object retData) {
		if (retData instanceof List) {
			String className = retData.getClass().getName();
			try {
				this.retData = Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.retData = retData;
		}
	}

}
