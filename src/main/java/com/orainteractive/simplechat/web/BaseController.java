package com.orainteractive.simplechat.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.orainteractive.simplechat.constant.SimpleChatConstant;

public class BaseController {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(SimpleChatConstant.DATE_PATTERN);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
