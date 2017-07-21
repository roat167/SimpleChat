package com.orainteractive.simplechat.exception;

public class PermissionDeniedException extends BaseException {
	private static final long serialVersionUID = 1L;

	public PermissionDeniedException(String errorMsg) {
		super(errorMsg);
	}
}
