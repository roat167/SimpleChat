package com.orainteractive.simplechat.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orainteractive.simplechat.constant.JwtConstant;
import com.orainteractive.simplechat.domain.User;
import com.orainteractive.simplechat.exception.BaseException;
import com.orainteractive.simplechat.exception.InvalidLoginException;
import com.orainteractive.simplechat.exception.UsernameNotFoundException;
import com.orainteractive.simplechat.po.LoginRequest;
import com.orainteractive.simplechat.po.UserTokenState;
import com.orainteractive.simplechat.security.CookieUtil;
import com.orainteractive.simplechat.security.TokenHelper;
import com.orainteractive.simplechat.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenHelper tokenHelper;
	
	@Value("${server.name}")
	private String serverName;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) throws BaseException {
		String jwtToken = "";

		if (loginRequest.getUsername() == null) {
			throw new BaseException("username can not be empty");
		}

		if (loginRequest.getPassword() == null) {
			throw new BaseException("password can not be empty");
		}

		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();

		User user = userService.getByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username is invalid");
		}

		String pwd = user.getPassword();

		if (!password.equals(pwd)) {
			throw new InvalidLoginException("Invalid login. Please check your username and password.");
		}
		String userid = user.getId().toString();
		jwtToken = tokenHelper.generateToken(username, userid);		
		
		CookieUtil.create(response, JwtConstant.TOKEN_COOKIE, jwtToken, false, -1, serverName);

		return jwtToken;
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {

		String authToken = tokenHelper.getToken(request);
		if (authToken != null && tokenHelper.canTokenBeRefreshed(authToken)) {
			// TODO check user password last update
			String refreshedToken = tokenHelper.refreshToken(authToken);

			Cookie authCookie = new Cookie(JwtConstant.TOKEN_COOKIE, (refreshedToken));
			authCookie.setPath("/");
			authCookie.setHttpOnly(true);
			authCookie.setMaxAge(JwtConstant.EXPIRES_IN);
			// Add cookie to response
			response.addCookie(authCookie);

			UserTokenState userTokenState = new UserTokenState(refreshedToken, JwtConstant.EXPIRES_IN);
			return ResponseEntity.ok(userTokenState);
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.accepted().body(userTokenState);
		}
	}

}
