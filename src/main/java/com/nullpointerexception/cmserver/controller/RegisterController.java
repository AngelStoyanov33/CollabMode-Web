package com.nullpointerexception.cmserver.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nullpointerexception.cmserver.model.User;
import com.nullpointerexception.cmserver.services.UserService;

@RestController
public class RegisterController {
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/register", produces = {"application/json"})
	public void index(@RequestBody Map<String, Object> payload) {
		User user = new User(payload.get("fullName").toString()
				, payload.get("email").toString()
				, payload.get("password").toString()
				, payload.get("newsletterStatus").toString());
		userService.addUser(user);
	}
}
