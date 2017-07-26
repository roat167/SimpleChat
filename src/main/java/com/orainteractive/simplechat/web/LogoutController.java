package com.orainteractive.simplechat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orainteractive.simplechat.constant.JwtConstant;
import com.orainteractive.simplechat.po.Response;
import com.orainteractive.simplechat.security.CookieUtil;
import com.orainteractive.simplechat.security.TokenHelper;

@RestController
public class LogoutController {

	@RequestMapping("/logout")
    public Response logout(HttpServletRequest request, HttpServletResponse response) {
        TokenHelper.invalidateRelatedTokens(request);
        CookieUtil.clear(response, JwtConstant.TOKEN_COOKIE);
        return new Response(HttpStatus.OK.value(), "Successfully logout!");
    }
}
