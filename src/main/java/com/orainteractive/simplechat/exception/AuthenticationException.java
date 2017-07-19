package com.orainteractive.simplechat.exception;

import javax.servlet.ServletException;

public class AuthenticationException extends ServletException {
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String errorMsg) {
		super(errorMsg);
	}
}
