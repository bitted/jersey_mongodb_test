package com.lakala.demo.beans;

public class RetStatus {

	private String retCode;
	private String errMsg;

	public RetStatus() {
	}

	public RetStatus(String retCode, String errMsg) {
		this.retCode = retCode;
		this.errMsg = errMsg;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
