package com.lakala.demo.exception;

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = -2324950845942304258L;
	
	private String errorCode;
	
	private String errorMsg;
	
	public ServiceException(String errorCode,String errorMsg){
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	

}
