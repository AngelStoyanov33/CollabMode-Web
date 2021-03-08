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
public class LoginController {
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/login", produces = {"application/json"})
	public String login(@RequestBody Map<String, Object> payload) {
		JSONObject json = new JSONObject();
		JWTManager jwtManager = new JWTManager();
		
		User user = userService.getUserByEmail(payload.get("emailAddress").toString());
		if(user != null) {
			if(user.getPassword().equals(payload.get("password").toString())) {
				json.put("status", "ok");
				json.put("token", jwtManager.createToken(payload.get("emailAddress").toString()));
			}else {
				json.put("status", "error");
				json.put("token", "");
				json.put("errorMessage", "Wrong password");
			}
		}else {
			json.put("status", "error");
			json.put("token", "");
			json.put("errorMessage", "User with that email does not exists");
		}
		
		return json.toString();
	}

}
