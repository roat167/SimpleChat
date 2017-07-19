package com.orainteractive.simplechat.exception;

public class UsernameNotFoundException extends BaseException {
	private static final long serialVersionUID = 1L;

	public UsernameNotFoundException(String errorMsg) {
		super(errorMsg);
	}
}
