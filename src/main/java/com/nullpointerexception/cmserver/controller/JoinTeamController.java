package com.nullpointerexception.cmserver.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nullpointerexception.cmserver.model.Team;
import com.nullpointerexception.cmserver.model.User;
import com.nullpointerexception.cmserver.services.JWTManager;
import com.nullpointerexception.cmserver.services.TeamService;
import com.nullpointerexception.cmserver.services.UserService;

@RestController
public class JoinTeamController {
	@Autowired
	TeamService teamService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/joinTeam", produces = {"application/json"})
	public String register(@RequestBody Map<String, Object> payload) {
		JSONObject json = new JSONObject();
		JWTManager jwtManager = new JWTManager();
		if(jwtManager.verifyAToken(payload.get("token").toString())) {
			Team team = teamService.getTeamByCode(payload.get("teamCode").toString());
			if(team != null) {
				User user = userService.getUserByEmail(jwtManager.decodeAToken(payload.get("token").toString()));
				if(user != null){
					user.setTeamID(team.getId());
					userService.addUser(user);
					json.put("status", "ok");
					
				}
			}else {
				json.put("status", "error");
				json.put("errorMessage", "Invalid team code");
			}
		}else {
			json.put("status", "error");
			json.put("errorMessage", "Unauthorized request");
		}
				
		return json.toString();
		
	}
}
