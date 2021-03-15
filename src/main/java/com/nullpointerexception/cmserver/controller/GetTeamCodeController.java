package com.nullpointerexception.cmserver.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nullpointerexception.cmserver.model.Team;
import com.nullpointerexception.cmserver.model.User;
import com.nullpointerexception.cmserver.services.JWTManager;
import com.nullpointerexception.cmserver.services.StringRandomizer;
import com.nullpointerexception.cmserver.services.TeamService;
import com.nullpointerexception.cmserver.services.UserService;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetTeamCodeController {
	@Autowired
	TeamService teamService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/sendInvite", produces = {"application/json"})
	public String register(@RequestBody Map<String, Object> payload) {
		JSONObject json = new JSONObject();
		JWTManager jwtManager = new JWTManager();
		
		if(jwtManager.verifyAToken(payload.get("token").toString())) {
		User user = userService.getUserByEmail(jwtManager.decodeAToken(payload.get("token").toString()));
		if(user != null) {
			Team team = teamService.getTeamById(user.getTeamID());
			json.put("status", "ok");
			json.put("teamCode", team.getCode());
		}
		}else {
			json.put("status", "error");
			json.put("errorMessage", "Unauthorized request");
		}
		
				
		return json.toString();
		
	}
}
