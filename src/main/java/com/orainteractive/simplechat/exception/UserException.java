package com.orainteractive.simplechat.exception;

public class UserException extends BaseException {
	private static final long serialVersionUID = 1L;
	private String errorMsg;
	private String errorCode = "Unknown Exception";

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public UserException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}

	public UserException(String errorMsg, String errorCode) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
