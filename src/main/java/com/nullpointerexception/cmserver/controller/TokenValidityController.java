package com.nullpointerexception.cmserver.controller;
import java.util.Map; 

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nullpointerexception.cmserver.services.JWTManager;
import com.nullpointerexception.cmserver.services.UserService;

@RestController
public class TokenValidityController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/checkTokenValidity", produces = {"application/json"})
	public String getTokenValidity(@RequestBody Map<String, Object> payload) {
		JSONObject json = new JSONObject();
		JWTManager jwtManager = new JWTManager();
		
		String token = payload.get("token").toString();
		
		if(jwtManager.verifyAToken(token)) {
			json.put("status", "ok");
		}else {
			json.put("status", "error");
			json.put("errorMessage", "Token is either expired or invalid");
		}
				
		return json.toString();
	}
}
