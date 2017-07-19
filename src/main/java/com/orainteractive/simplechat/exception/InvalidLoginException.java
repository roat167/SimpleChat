package com.orainteractive.simplechat.exception;

public class InvalidLoginException extends BaseException {
	private static final long serialVersionUID = 1L;

	public InvalidLoginException(String errorMsg) {
		super(errorMsg);
	}
}
