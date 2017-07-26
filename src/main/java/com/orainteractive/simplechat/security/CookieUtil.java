package com.orainteractive.simplechat.security;

import org.springframework.web.util.WebUtils;

import com.orainteractive.simplechat.constant.JwtConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	public static void create(HttpServletResponse httpServletResponse, String name, String value, 
			Boolean secure, Integer maxAge, String domain) {
		Cookie cookie = new Cookie(name, value);
		cookie.setSecure(secure); //work on HTTPS only.
		cookie.setHttpOnly(true); // invisible to JavaScript.
		cookie.setMaxAge(maxAge); 
		cookie.setDomain(domain);
		cookie.setPath("/");
		httpServletResponse.addCookie(cookie);
	}

	public static void clear(HttpServletResponse httpServletResponse, String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath("/"); //visible to all paths.
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0); //expire cookie now
		cookie.setDomain(JwtConstant.DOMAIN);
		httpServletResponse.addCookie(cookie);
	}

	public static String getValue(HttpServletRequest httpServletRequest, String name) {
		Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
		return cookie != null ? cookie.getValue() : null;
	}
}
