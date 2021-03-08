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
public class GetUserController {
		@Autowired
		UserService userService;
		
		@RequestMapping(method = RequestMethod.POST, value = "/getUser", produces = {"application/json"})
		public String getUserDetails(@RequestBody Map<String, Object> payload) {
			JSONObject json = new JSONObject();
			JWTManager jwtManager = new JWTManager();
			
			String token = payload.get("token").toString();
			String userEmail = jwtManager.decodeAToken(token);
			if(userEmail != null) {
				User user = userService.getUserByEmail(userEmail);
				if(user != null) {
					json.put("status", "ok");
					json.put("userID", user.getId());
					json.put("userFullName", user.getFullName());
					json.put("userEmail", user.getEmail());
				}else {
					json.put("status", "error");
					json.put("errorMessage", "Could not fetch user details");
				}
			}else {
				json.put("status", "error");
				json.put("errorMessage", "Could not fetch user details");
			}
			
			return json.toString();
		}

	}


