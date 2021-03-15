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
import com.nullpointerexception.cmserver.services.TeamService;
import com.nullpointerexception.cmserver.services.UserService;

@RestController
public class TransferTeamOwnershipController {
	@Autowired
	TeamService teamService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/transferTeamOwnerShip", produces = {"application/json"})
	public String transferTeamOwnership(@RequestBody Map<String, Object> payload) {
		JSONObject json = new JSONObject();
		JWTManager jwtManager = new JWTManager();
		if(jwtManager.verifyAToken(payload.get("token").toString())) {
			User user = userService.getUserByEmail(jwtManager.decodeAToken(payload.get("token").toString()));
			int newOwnerID = Integer.parseInt(payload.get("newOwnerID").toString());
			User newOwner = userService.getUserById(newOwnerID);
			if(newOwner != null) {
				user.setOwner(false);
				newOwner.setOwner(true);
				userService.addUser(user);
				userService.addUser(newOwner);
				json.put("status", "ok");
			}

		}else {
			json.put("status", "error");
			json.put("errorMessage", "Unauthorized request");
		}
				
		return json.toString();
		
	}
}
