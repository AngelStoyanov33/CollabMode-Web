package com.nullpointerexception.cmserver.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nullpointerexception.cmserver.model.User;
import com.nullpointerexception.cmserver.services.JWTManager;
import com.nullpointerexception.cmserver.services.UserService;

@RestController
public class RegisterController {
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/register", produces = {"application/json"})
	public String register(@RequestBody Map<String, Object> payload) {
		JSONObject json = new JSONObject();
		JWTManager jwtManager = new JWTManager();
		User user = new User(payload.get("fullName").toString()
				, payload.get("email").toString()
				, payload.get("password").toString()
				, payload.get("newsletterStatus").toString());
				
		if(userService.getUserByEmail(user.getEmail()) != null) {
			json.put("status", "error");
			json.put("token", "");
			json.put("errorMessage", "User with that email already exists!");
		}else {
			userService.addUser(user);
			json.put("status", "ok");
			json.put("token", jwtManager.createToken(payload.get("email").toString()));
		}
		
		return json.toString();
		
	}
}
