package com.orainteractive.simplechat.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import com.orainteractive.simplechat.constant.JwtConstant;
import com.orainteractive.simplechat.exception.AuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {
	private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader(JwtConstant.AUTH_HEADER);

		logger.debug("Header " + authHeader);
		if ("OPTIONS".equals(request.getMethod())) { //This need only if you want to by pass request which header is 'options'
			response.setStatus(HttpServletResponse.SC_OK);

			chain.doFilter(req, res);
		} else {

			if (authHeader == null || !authHeader.startsWith(JwtConstant.HEADER_PREFIX)) {
				throw new AuthenticationException("Missing or invalid Authorization header");
			}
			// The part after Bearer length = 7
			// Extract the token from the HTTP Authorization header
			final String token = authHeader.substring(7);

			try {
				// validate the token
				final Claims claims = Jwts.parser().setSigningKey(JwtConstant.SECRET).parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
				logger.debug("claim " + claims);

			} catch (final SignatureException e) {
				logger.debug("Invalid token " + token);
				throw new AuthenticationException("Invalid token");
			}

			chain.doFilter(req, res);
		}
	}
}
