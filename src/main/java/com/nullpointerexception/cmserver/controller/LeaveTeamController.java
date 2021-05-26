package com.nullpointerexception.cmserver.controller;

import java.util.List;
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
public class LeaveTeamController {
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/leave", produces = {"application/json"})
	public String leaveTeam(@RequestBody Map<String, Object> payload ) {
		JSONObject json = new JSONObject();
		JWTManager jwtManager = new JWTManager();
		
		if(jwtManager.verifyAToken(payload.get("token").toString())) {
			User user = userService.getUserByEmail(jwtManager.decodeAToken(payload.get("token").toString()));
			if(user.getTeamID() != 0  && !user.isOwner()) {
				user.setTeamID(0);
				userService.addUser(user);
				json.put("status", "ok");
			}else {
				List<User> users = userService.getUsersByTeamID(user.getTeamID());
				if(users.size() == 1) {
					user.setTeamID(0);
					userService.addUser(user);
					json.put("status", "ok");
				}else {
					json.put("status", "error");
					json.put("errorMessage", "You must kick all members from your team or transfer the ownership before leaving");
				
				}
			}
		}else {
			json.put("status", "error");
			json.put("errorMessage", "Unauthorized request");
		}
		
		return json.toString();
	}

}
