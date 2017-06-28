package com.orainteractive.simplechat.exception;

public class ChatMessageException extends BaseException {
	private static final long serialVersionUID = 1L;
	private String errorMsg;

	public ChatMessageException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
