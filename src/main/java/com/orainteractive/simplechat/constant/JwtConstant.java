package com.orainteractive.simplechat.constant;

public class JwtConstant {
	//public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
	public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/login";
	public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";
	public static final String TOKEN_REFRESH_ENTRY_POINT = "/token";
	public static String HEADER_PREFIX = "Bearer ";
	
	public static final String TOKEN_COOKIE = "coco";
	public static final String APP_NAME = "simplechat";
	public static final String SECRET = "secretkey";    
	public static final int EXPIRES_IN = 600;    
	public static final String AUTH_HEADER = "authorization";    
	public static final String AUTH_COOKIE = "AUTH-TOKEN";
	public static final String USER_COOKIE = "ora-u";
	public static final String DOMAIN = "localhost";
}
