package com.orainteractive.simplechat.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.orainteractive.simplechat.constant.JwtConstant;
import com.orainteractive.simplechat.exception.AuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
/**
 * Using OncePerRequestFilter prevents the chain from calling the filter multiple times per request
 */
public class JwtFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);	
	
	@Override
	protected void doFilterInternal(final HttpServletRequest req, final HttpServletResponse res, final FilterChain chain)
			throws IOException, ServletException {
		
		final String authHeader = req.getHeader(JwtConstant.AUTH_HEADER);

		logger.debug("Header " + authHeader);
		if ("OPTIONS".equals(req.getMethod())) { //This need only if you want to by pass request which header is 'options'
			res.setStatus(HttpServletResponse.SC_OK);

			chain.doFilter(req, res);
		} else {
			
			//Get token value from cookie
			String token = CookieUtil.getValue(req, JwtConstant.TOKEN_COOKIE);
			logger.debug("TokenCookie::: " + token);
	        if (token == null) {
	        	throw new AuthenticationException("Missing or invalid Authorization header");
	        }
	        
	        //if (authHeader == null || !authHeader.startsWith(JwtConstant.HEADER_PREFIX)) {
	        //	throw new AuthenticationException("Missing or invalid Authorization header");
	        //}
			// The part after Bearer length = 7
			// Extract the token from the HTTP Authorization header
			//final String token = authHeader.substring(7);			

			try {
				// validate the token				
				final Claims claims = TokenHelper.getClaimsFromToken(token);
				req.setAttribute("claims", claims);
				logger.debug("claim " + claims);

			} catch (final SignatureException e) {
				logger.debug("Invalid token " + token);
				throw new AuthenticationException("Invalid token");
			}

			chain.doFilter(req, res);
		}
	}	
}
