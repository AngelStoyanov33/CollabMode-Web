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
public class KickFromTeamController {
	@Autowired
	TeamService teamService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/kick", produces = {"application/json"})
	public String kickFromTeam(@RequestBody Map<String, Object> payload) {
		JSONObject json = new JSONObject();
		JWTManager jwtManager = new JWTManager();
		if(jwtManager.verifyAToken(payload.get("token").toString())) {
			User user = userService.getUserByEmail(jwtManager.decodeAToken(payload.get("token").toString()));
			if(user.isOwner() == true) {
				int kickedMemberID = Integer.parseInt(payload.get("kickedMemberID").toString());
				User kickedMember = userService.getUserById(kickedMemberID);
				if(kickedMember != null) {
					kickedMember.setTeamID(0);
					userService.addUser(kickedMember);
					json.put("status", "ok");
				}
			}else {
				json.put("status", "error");
				json.put("errorMessage", "You must be the team owner to be able to kick a teammate!");
			}

		}else {
			json.put("status", "error");
			json.put("errorMessage", "Unauthorized request");
		}
				
		return json.toString();
		
	}
}
