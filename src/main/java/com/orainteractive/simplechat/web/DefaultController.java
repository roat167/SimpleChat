package com.orainteractive.simplechat.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		System.out.println("Homepage");
		return "home";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		System.out.println("Error page");
		return "404";
	}
}
