package com.orainteractive.simplechat.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orainteractive.simplechat.domain.User;
import com.orainteractive.simplechat.exception.BaseException;
import com.orainteractive.simplechat.service.UserService;

@RestController
public class RegisterController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) throws BaseException {		
		return new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
	}

}
