package com.orainteractive.simplechat.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConstant {
	public static String HEADER_PREFIX = "Bearer ";

	public static String TOKEN_COOKIE;
	public static String APP_NAME;
	public static String SECRET;
	public static int EXPIRES_IN;
	public static String AUTH_HEADER;
	public static String AUTH_COOKIE;
	public static String DOMAIN;

	@Value("${jwt.token-cookie}")
	private void setTokenCookie(String tokenCookie) {
		TOKEN_COOKIE = tokenCookie;
	}

	@Value("${spring.application.name}")
	private void setApplicationName(String appName) {
		APP_NAME = appName;
	}

	@Value("${jwt.secretkey}")
	private void setSecretKey(String key) {
		SECRET = key;
	}

	@Value("${jwt.expired-in}")
	private void setExpiredDate(int expireIn) {
		EXPIRES_IN = expireIn;
	}

	@Value("${jwt.auth-header}")
	private void setAuthHeader(String header) {
		AUTH_HEADER = header;
	}

	@Value("${jwt.auth-cookie}")
	private void setAuthCookie(String authCookie) {
		AUTH_COOKIE = authCookie;
	}

	@Value("${server.name}")
	private void setDomain(String domain) {
		DOMAIN = domain;
	}
}
